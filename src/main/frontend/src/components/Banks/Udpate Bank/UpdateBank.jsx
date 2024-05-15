import React, { useState } from 'react';
import axios from 'axios';

export const UpdateBank = ({ onSubmit }) => {
    const [name, setName] = useState('');
    const [numberOfUsers, setNumberOfUsers] = useState(0);
    const [percentPerYear, setPercentPerYear] = useState(0);
    const [dateOfFoundation, setDateOfFoundation] = useState('');

    // Assuming userId is passed as a prop to this component
    const userId = 'someUserId'; // Replace 'someUserId' with actual user ID

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.put(`http://localhost:8080/api/bank/update_bank/${userId}`, { name, numberOfUsers, percentPerYear, dateOfFoundation });
            onSubmit(response.data);
        } catch (error) {
            console.error('Error updating bank:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
            </label>
            <label>
                Number of Users:
                <input type="number" value={numberOfUsers} onChange={(e) => setNumberOfUsers(parseInt(e.target.value))} required />
            </label>
            <label>
                Percent Per Year:
                <input type="number" value={percentPerYear} onChange={(e) => setPercentPerYear(parseInt(e.target.value))} required />
            </label>
            <label>
                Date of Foundation:
                <input type="date" value={dateOfFoundation} onChange={(e) => setDateOfFoundation(e.target.value)} required />
            </label>
            <button type="submit">Update</button>
        </form>
    );
};
