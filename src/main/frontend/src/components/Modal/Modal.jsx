import React, { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

export const UpdateUserModal = ({ user, onUpdateUser, onClose }) => {
    const [firstName, setFirstName] = useState(user.firstName);
    const [lastName, setLastName] = useState(user.lastName);
    const [email, setEmail] = useState(user.email);

    const handleUpdateUser = () => {
        const updatedUser = {
            ...user,
            firstName: firstName,
            lastName: lastName,
            email: email,
        };
        onUpdateUser(updatedUser);
        handleClose();
    };

    const handleClose = () => {
        setFirstName(user.firstName);
        setLastName(user.lastName);
        setEmail(user.email);
    };



    return (
        <Modal show={true} onHide={handleClose}>
            <Modal.Header closeButton onClick={onClose}>
                <Modal.Title>Редактировать пользователя</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div>
                    <label>Имя:</label>
                    <input type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                </div>
                <div>
                    <label>Фамилия:</label>
                    <input type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} />
                </div>
                <div>
                    <label>Email:</label>
                    <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onClose}>Закрыть</Button>
                <Button variant="primary" onClick={handleUpdateUser}>
                    Сохранить изменения
                </Button>
            </Modal.Footer>
        </Modal>
    );
};
