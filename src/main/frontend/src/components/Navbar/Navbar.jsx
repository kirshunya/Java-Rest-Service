import React from 'react';
import Navbar from 'react-bootstrap/Navbar';
import Nav from 'react-bootstrap/Nav';
import Container from 'react-bootstrap/Container';
import NavDropdown from 'react-bootstrap/NavDropdown'; // Импорт NavDropdown
import styles from './Navbar.module.css'

export function MyNavbar() {
    return (
        <Navbar className={styles.navbarContainer}>
            <Container>
                <Navbar.Brand href="#home">Navigation Bar</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="/users">Пользователи</Nav.Link>
                        <NavDropdown title="Списки" id="basic-nav-dropdown">
                            <NavDropdown.Item href="/users">Пользователи</NavDropdown.Item>
                            <NavDropdown.Item href="/banks">Банки</NavDropdown.Item>
                            <NavDropdown.Item href="/credits">Кредиты</NavDropdown.Item>
                            <NavDropdown.Divider />
                            <NavDropdown.Item href="#action/3.4">Разделенный ссылок</NavDropdown.Item>
                        </NavDropdown>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

