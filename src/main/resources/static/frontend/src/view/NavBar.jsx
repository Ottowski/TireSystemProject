import { useEffect, useState } from 'react';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom';

function NavBar() {
  const [user, setUser] = useState(null);
  const localUser = JSON.parse(localStorage.getItem('user'));

  useEffect(() => {
    if (localUser) {
      setUser(localUser);
    }
  }, []);
    
    console.log(user);

  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">Navbar with text</Navbar.Brand>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end">
          <Navbar.Text>
            {user ? user.username : <Link to={'/login'}>Login</Link>}
          </Navbar.Text>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavBar;
