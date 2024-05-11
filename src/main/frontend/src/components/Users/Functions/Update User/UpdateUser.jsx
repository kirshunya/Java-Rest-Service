// EditUser.jsx
import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {useNavigate, useParams} from "react-router-dom";

export const UpdateUser = () => {
    const { id } = useParams();
    const [user, setUser] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/user/${id}`);
                setUser(response.data);
            } catch (error) {
                console.error("Failed to fetch user:", error);
            }
        };

        fetchUser();
    }, [id]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.put(`http://localhost:8080/api/user/update/${id}`, user);
            navigate('/users'); // Перенаправление обратно на страницу списка пользователей после обновления
        } catch (error) {
            console.error("Failed to update user:", error);
        }
    };

    return (
        <div>
            <h3>Редактирование пользователя</h3>
            <form onSubmit={handleSubmit}>
                <input type="text" value={user.firstName} onChange={(e) => setUser({...user, firstName: e.target.value})} />
                <input type="text" value={user.lastName} onChange={(e) => setUser({...user, lastName: e.target.value})} />
                <input type="email" value={user.email} onChange={(e) => setUser({...user, email: e.target.value})} />
                <button type="submit">Сохранить</button>
            </form>
        </div>
    );
};


