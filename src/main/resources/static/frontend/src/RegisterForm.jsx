import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import { useState } from 'react';
import { createUser } from './components/Auth';
import { useNavigate } from 'react-router-dom';
// import { createUser } from 'Auth.js';

function RegisterForm() {
  const [user, setUser] = useState({
    username: '',
    password: '',
    roles: ''
    // vehicles: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    e.preventDefault;
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  console.log(user);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await createUser(user);
      if (res) {
        navigate('/login')
      }

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
                  <InputGroup
                    className="mb-3"
                    onChange={handleChange}
                 
                    
                  >
                    <InputGroup.Text id="inputGroup-sizing-default">
                      Username
                    </InputGroup.Text>
                    <Form.Control
                      aria-label="Default"
                      aria-describedby="inputGroup-sizing-default"
                      name='username'
                    />
                  </InputGroup>

                  <InputGroup
                    className="mb-3"
                    onChange={handleChange}
        
                  >
                    <InputGroup.Text id="inputGroup-sizing-default">
                      Password
                    </InputGroup.Text>
                    <Form.Control
                      aria-label="Default"
                      aria-describedby="inputGroup-sizing-default"
                      name='password'
                    />
                  </InputGroup>
                  

                  <Form.Select
                    aria-label="Default select Role"
                    onChange={handleChange}
                    name='roles'
                  >
                    <option>Open this select menu</option>
                    <option value="ADMIN">Admin</option>
                    <option value="USER">User</option>
                  </Form.Select>
                </Modal.Body>

                <Modal.Footer>
                  <Button variant="secondary">Close</Button>
                  <Button variant="primary" type='submit'>Register</Button>
                </Modal.Footer>
              </Modal.Dialog>
            </div>
          </form>
        </>
      );
    }
  

export default RegisterForm;
