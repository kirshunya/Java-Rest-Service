import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './Banks.module.css';
import { MyNavbar } from "../../Navbar/Navbar.jsx";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

export const Banks = () => {
    const [banks, setBanks] = useState([]);

    const deleteBank = async (Id) => {
        try {
            await axios.delete(`http://localhost:8080//api/bank/delete_bank/${Id}`);
            fetchUsers();
        } catch (error) {
            console.error("Failed to delete bank:", error);
            alert("Ошибка при удалении пользователя. Пожалуйста, попробуйте снова.");
        }
    };

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/bank/all'); // Замените на ваш API endpoint
                setBanks(response.data);
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
                    <th className={styles.tableHeader}>Name</th>
                    <th className={styles.tableHeader}>Number of Users</th>
                    <th className={styles.tableHeader}>Percent per year</th>
                    <th className={styles.tableHeader}>Date of foundation</th>
                    <th className={styles.tableHeader}>Actions</th>
                </tr>
                </thead>
                <tbody>
                {banks.map((banks) => (
                    <tr key={banks.id}>
                        <td className={styles.tableCell}>{banks.id}</td>
                        <td className={styles.tableCell}>{banks.name}</td>
                        <td className={styles.tableCell}>{banks.numberOfUsers}</td>
                        <td className={styles.tableCell}>{banks.percentPerYear}</td>
                        <td className={styles.tableCell}>{banks.dateOfFoundation}</td>
                        <td className={styles.tableCell}>
                            {/*<Button variant="danger" onClick={() => deleteBank(banks.id)}>Удалить</Button>*/}
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};