import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from './User.module.css';
import {useNavigate, useParams} from 'react-router-dom';
import {MyNavbar} from "../../../Navbar/Navbar.jsx";
import Button from "react-bootstrap/Button"; // Импортируйте useParams

export const UserDetails = () => {
    const { userId } = useParams(); // Используйте useParams для получения userId
    const [user, setUser] = useState(null);
    const navigate = useNavigate(); // Получаем функцию navigate

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/user/${userId}`); // Используйте userId из useParams
                setUser(response.data);
            } catch (error) {
                console.error("Failed to fetch user:", error);
            }
        };

        fetchUser();
    }, [userId]); // userId теперь динамический и будет обновляться при изменении URL

    if (!user) {
        return <div>Loading...</div>;
    }

    const deleteUser = async () => {
        try {
            await axios.delete(`http://localhost:8080/api/user/delete_user/${userId}`);
            navigate(-1); // Возвращаемся на предыдущую страницу после удаления
        } catch (error) {
            console.error("Failed to delete user:", error);
        }
    };

    const updateUser = async (updatedUser) => {
        try {
            await axios.put(`http://localhost:8080/api/user/${userId}`, updatedUser);
            // Обновляем состояние пользователя на странице
            setUser(updatedUser);
            navigate(-1); // Возвращаемся на предыдущую страницу после обновления
        } catch (error) {
            console.error("Failed to update user:", error);
        }
    };

    return (
        <div className={styles.userDetails}>
            <MyNavbar />
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
                    <li
                        key={bank.id}>
                        <p> Bank: {bank.name} </p>
                        <p>Foundation:{bank.foundation}  </p>
                        <p>Number of Users: {bank.numberOfUsers} </p>
                        <p>Percent Per Year: {bank.percentPerYear}</p>
                        <p> </p>
                    </li>
                ))}
            </ul>
            {/* Кнопка удаления */}
            <span> <button onClick={deleteUser}>Удалить</button> </span>

            {/* Кнопка обновления */}
            <span><button onClick={() => navigate(`/update-user/${userId}`)}>Обновить</button> </span>

           <button onClick={() => navigate(-1)}>Назад</button>

        </div>
    );
};
