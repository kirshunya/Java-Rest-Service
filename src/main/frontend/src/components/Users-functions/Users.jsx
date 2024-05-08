import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './Users.module.css';
import { MyNavbar } from "../navbar/Navbar.jsx";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

export const Users = () => {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/user/all'); // Замените на ваш API endpoint
                setUsers(response.data);
            } catch (error) {
                console.error("Failed to fetch users:", error);
            }
        };

        fetchUsers();
    }, []);

    return (
        <div className={styles.userList}>
            <MyNavbar />
            <h3>Список пользователей</h3>
            <br/>
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
                {users.map((user) => (
                    <tr key={user.id}>
                        <td className={styles.tableCell}>{user.id}</td>
                        <td className={styles.tableCell}>{user.firstName}</td>
                        <td className={styles.tableCell}>{user.lastName}</td>
                        <td className={styles.tableCell}>{user.dateOfReg}</td>
                        <td className={styles.tableCell}>{user.email}</td>
                        <td className={styles.tableCell}>{user.sucCredits}</td>
                        <td className={styles.tableCell}>{user.failCredits}</td>
                        <td className={styles.tableCell}>
                            <Button variant="primary" onClick={() => handleEditUser(user.id)}>Редактировать</Button>{' '}
                            <Button variant="danger" onClick={() => handleDeleteUser(user.id)}>Удалить</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};