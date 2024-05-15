import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './Credits.module.css';
import { MyNavbar } from "../components/Navbar/Navbar.jsx";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

export const Credits = () => {
    const [credits, setCredits] = useState([]);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/credits/all'); // Замените на ваш API endpoint
                setCredits(response.data);
            } catch (error) {
                console.error("Failed to fetch users:", error);
            }
        };

        fetchUsers();
    }, []);

    return (
        <div className={styles.userList}>
            <MyNavbar />
            <h3>Список банков</h3>
            <br/>
            <Table className={styles.table}>
                <thead>
                <tr>
                    <th className={styles.tableHeader}>Id</th>
                    <th className={styles.tableHeader}>Value</th>
                    <th className={styles.tableHeader}>End of Credit</th>
                    <th className={styles.tableHeader}>Actions</th>
                </tr>
                </thead>
                <tbody>
                {credits.map((credits) => (
                    <tr key={credits.id}>
                        <td className={styles.tableCell}>{credits.id}</td>
                        <td className={styles.tableCell}>{credits.value}</td>
                        <td className={styles.tableCell}>{credits.endOfCredit}</td>
                        <td className={styles.tableCell}>
                            {/*<Button variant="primary" onClick={() => handleEditUser(credits.id)}>Редактировать</Button>{' '}*/}
                           {/*<Button variant="danger" onClick={() => handleDeleteUser(credits.id)}>Удалить</Button>*/}
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};