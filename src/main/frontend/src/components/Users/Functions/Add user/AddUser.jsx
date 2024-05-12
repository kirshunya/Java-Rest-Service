import {useNavigate} from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import styles from './AddUser.module.css';
import {MyNavbar} from "../../../Navbar/Navbar.jsx";

export const AddUser = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [sucCredits, setSucCredits] = useState(0);
    const [failCredits, setFailCredits] = useState(0);
    const [dateOfReg, setDateOfReg] = useState(new Date().toISOString().split('T')[0]); // Текущая дата
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/user/register', {
                firstName,
                lastName,
                email,
                sucCredits,
                failCredits,
                dateOfReg
            });
            console.log(response.data);
            navigate(-1);
            // Здесь можно добавить логику для обработки успешного добавления пользователя
        } catch (error) {
            console.error("Ошибка при добавлении пользователя:", error);
            // Обработка ошибки
        }
    };

    const handleCreditChange = (e, setFunction) => {
        const value = e.target.value;
        if (value >= 0) {
            setFunction(Number(value));
        }
    };

    return (
        <div>
            <MyNavbar />
            <form className={styles.form} onSubmit={handleSubmit}>

                <label>
                    Имя:
                    <input type="text" className={styles.input} value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
                </label>
                <label>
                    Фамилия:
                    <input type="text" className={styles.input} value={lastName} onChange={(e) => setLastName(e.target.value)} required />
                </label>
                <label>
                    Email:
                    <input type="email" className={styles.input} value={email} onChange={(e) => setEmail(e.target.value)} required />
                </label>
                <label>
                    Suc Credits:
                    <input type="number" className={styles.input} value={sucCredits} onChange={(e) => handleCreditChange(e, setSucCredits)} required />
                </label>
                <label>
                    Fail Credits:
                    <input type="number" className={styles.input} value={failCredits} onChange={(e) => handleCreditChange(e, setFailCredits)} required />
                </label>
                <label>
                    Date of Registration:
                    <input type="date" className={styles.input} value={dateOfReg} onChange={(e) => setDateOfReg(e.target.value)} required />
                </label>
                <button type="submit" className={styles.button}>Добавить пользователя</button>
            </form>
        </div>
    );
};


