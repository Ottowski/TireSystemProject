
import React from "react";
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useState } from 'react';
import { loginUser } from "../components/Auth";
import { Link } from "react-router-dom";



export default function LoginForm() {
    
        const [user, setUser] = useState({
            username: '',
            password: '',
      
        });

        const handleChange = (e) => {
            e.preventDefault;
            setUser({ ...user, [e.target.name]: e.target.value });
        };

        console.log(user);

        const handleSubmit = async (e) => {
            e.preventDefault();
            try {
                const res = await loginUser(user);

                console.log(res);
            } catch (error) {
                console.log(error);
            }
        }

        return (
            <>
                <form onSubmit={handleSubmit}>
                    <div
                        className="modal show"
                        style={{ display: 'block', position: 'initial' }}
                    >
                        <Modal.Dialog>
                            <Modal.Header closeButton>
                                <Modal.Title>Modal title</Modal.Title>
                            </Modal.Header>

                            <Modal.Body>
                                <InputGroup className="mb-3" onChange={handleChange}>
                                    <InputGroup.Text id="inputGroup-sizing-default">
                                        Username
                                    </InputGroup.Text>
                                    <Form.Control
                                        aria-label="Default"
                                        aria-describedby="inputGroup-sizing-default"
                                        name="username"
                                    />
                                </InputGroup>

                                <InputGroup className="mb-3" onChange={handleChange}>
                                    <InputGroup.Text id="inputGroup-sizing-default">
                                        Password
                                    </InputGroup.Text>
                                    <Form.Control
                                        aria-label="Default"
                                        aria-describedby="inputGroup-sizing-default"
                                        name="password"
                                    />
                                </InputGroup>

                            </Modal.Body>

                            <Modal.Footer>
                          
                                <Button variant="primary" type="submit">
                                    login
                                </Button>
                                <span>
                                    <Link to={'/register'}>Already Register</Link>
                                </span>
                            </Modal.Footer>
                        </Modal.Dialog>
                    </div>
                </form>
            </>
        );
    }


