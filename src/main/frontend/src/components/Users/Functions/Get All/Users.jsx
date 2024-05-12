import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './Users.module.css';
import { MyNavbar } from "../../../Navbar/Navbar.jsx";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';
import search_style from '../../../SearchBar/Search.module.css';
import { UpdateUserModal } from '../../../Modal/Modal.jsx';

export const Users = () => {
    const [users, setUsers] = useState([]);
    const [searchName, setSearchName] = useState('');
    const [selectedUser, setSelectedUser] = useState(null);
    const [showModal, setShowModal] = useState(false);
    const navigate = useNavigate();

    const handleCloseModal = () => {
        setShowModal(false);
    };

    const fetchUsers = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/user/all');
            setUsers(response.data);
        } catch (error) {
            console.error("Failed to fetch users:", error);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    const deleteUser = async (userId) => {
        try {
            await axios.delete(`http://localhost:8080/api/user/delete_user/${userId}`);
            fetchUsers();
        } catch (error) {
            console.error("Failed to delete user:", error);
            alert("Ошибка при удалении пользователя. Пожалуйста, попробуйте снова.");
        }
    };

    const handleCheckUser = (userId) => {
        navigate(`/user/${userId}`);
    };

    const handleSearchChange = (event) => {
        setSearchName(event.target.value);
    };

    const handleEditUser = (user) => {
        setSelectedUser(user);
        setShowModal(true);
    };

    const handleUpdateUser = async (updatedUser) => {
        try {
            await axios.put(`http://localhost:8080/api/user/update_user/${updatedUser.id}`, updatedUser);
            fetchUsers();
            setSelectedUser(updatedUser); // Обновляем выбранного пользователя
            setShowModal(false); // Закрываем модальное окно
        } catch (error) {
            console.error("Failed to update user:", error);
            alert("Ошибка при обновлении пользователя. Пожалуйста, попробуйте снова.");
        }
    };

    const filteredUsers = users.filter(user => user.lastName.toLowerCase().includes(searchName.toLowerCase()));

    return (
        <div className={styles.userList}>
            <MyNavbar />
            <h3>Список пользователей</h3>
            <div className={search_style.searchContainer}>
                <input
                    type="text"
                    placeholder="Поиск по фамилии"
                    value={searchName}
                    onChange={handleSearchChange}
                    className="searchInput"
                />
                <Button variant="primary" onClick={() => navigate('/add-user')}>Добавить пользователя</Button>
            </div>

            <Table className={styles.table}>
                <thead>
                <tr>
                    <th className={styles.tableHeader}>Id</th>
                    <th className={styles.tableHeader}>First Name</th>
                    <th className={styles.tableHeader}>Last Name</th>
                    <th className={styles.tableHeader}>Date of Registration</th>
                    <th className={styles.tableHeader}>Email</th>
                    <th className={styles.tableHeader}>Suc Credits</th>
                    <th className={styles.tableHeader}>Fail Credits</th>
                    <th className={styles.tableHeader}>Actions</th>
                </tr>
                </thead>
                <tbody>
                {filteredUsers.map((user) => (
                    <tr key={user.id}>
                        <td className={styles.tableCell}>{user.id}</td>
                        <td className={styles.tableCell}>{user.firstName}</td>
                        <td className={styles.tableCell}>{user.lastName}</td>
                        <td className={styles.tableCell}>{user.dateOfReg}</td>
                        <td className={styles.tableCell}>{user.email}</td>
                        <td className={styles.tableCell}>{user.sucCredits}</td>
                        <td className={styles.tableCell}>{user.failCredits}</td>
                        <td className={styles.tableCell}>
                            <Button variant="success" onClick={() => handleCheckUser(user.id)}>Просмотр пользователя</Button>
                            <Button variant="primary" onClick={() => handleEditUser(user)}>Редактировать</Button>
                            <Button variant="danger" onClick={() => deleteUser(user.id)}>Удалить</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>

            {showModal && (
                <UpdateUserModal
                    user={selectedUser}
                    onUpdateUser={handleUpdateUser}
                    onClose={handleCloseModal}
                />
            )}
        </div>
    );
};

