import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './User.module.css';
import { useNavigate, useParams } from 'react-router-dom';
import { MyNavbar } from '../../../Navbar/Navbar.jsx';
import Button from 'react-bootstrap/Button';
import { UpdateUserModal } from '../../../Modal/Modal.jsx';

export const UserDetails = () => {
    const {userId} = useParams();
    const [user, setUser] = useState(null);
    const navigate = useNavigate();
    const [showModal, setShowModal] = useState(false);
    const [selectedUser, setSelectedUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/user/${userId}`);
                setUser(response.data);
            } catch (error) {
                console.error("Failed to fetch user:", error);
            }
        };

        fetchUser();
    }, [userId]);

    if (!user) {
        return <div>Loading...</div>;
    }

    const deleteUser = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/user/delete_user/${userId}`);
            navigate(-1);
        } catch (error) {
            console.error("Failed to delete user:", error);
        }
    };

    const handleEditUser = (user) => {
        setSelectedUser(user);
        setShowModal(true);
    };

    const handleUpdateUser = async (updatedUser) => {
        try {
            await axios.put(`http://localhost:8080/api/user/update_user/${updatedUser.id}`, updatedUser);
            setUser(updatedUser);
            setShowModal(false);
        } catch (error) {
            console.error("Failed to update user:", error);
            alert("Ошибка при обновлении пользователя. Пожалуйста, попробуйте снова.");
        }
    };

    const handleCloseModal = () => {
        setShowModal(false);
    };

    return (
        <div className={styles.userDetails}>
            <MyNavbar/>
            <h2>{`${user.firstName} ${user.lastName}`}</h2>
            <p>ID: {user.id}</p>
            <p>Email: {user.email}</p>
            <p>Suc Credits: {user.sucCredits}</p>
            <p>Fail Credits: {user.failCredits}</p>
            <p>Date of Registration: {user.dateOfReg}</p>

            <h3>Credit</h3>
            <p>Limit: {user.credit && user.credit.limit}</p>
            <p>Value: {user.credit && user.credit.value}</p>
            <p>End of Credit: {user.credit && user.credit.endOfCredit}</p>

            <h3>Banks</h3>
            <ul>
                {user.banks && user.banks.map(bank => (
                    <li key={bank.id}>
                        <p>Bank: {bank.name}</p>
                        <p>Foundation:{bank.foundation}</p>
                        <p>Number of Users: {bank.numberOfUsers}</p>
                        <p>Percent Per Year: {bank.percentPerYear}</p>
                        <p></p>
                    </li>
                ))}
            </ul>

            <span> <button onClick={deleteUser}>Удалить</button> </span>
             <span> <button onClick={() => handleEditUser(user)}>Обновить</button> </span>


            {showModal && (
                <UpdateUserModal
                    user={selectedUser}
                    onUpdateUser={handleUpdateUser}
                    onClose={handleCloseModal}
                />
            )}

            <button onClick={() => navigate(-1)}>Назад</button>
        </div>
    );
};