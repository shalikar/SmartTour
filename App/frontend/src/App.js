import "./App.css";
import HomePage from "./components/Pages/HomePage";
import { Container, Navbar } from "react-bootstrap";

import DetailsPage from "./components/Pages/DetailsPage";
import { BrowserRouter as Router } from "react-router-dom";
import { Route, Switch, Link } from "react-router-dom";

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar bg="dark" expand="lg">
          <Navbar.Brand>
            <Link
              style={{
                textDecoration: "none",
                color: "white",
                cursor: "pointer",
                display: "block",
              }}
              to="/"
            >
              <h4>Smart Museum Tour</h4>
            </Link>
          </Navbar.Brand>
        </Navbar>
        <Container fluid="md">
          <Switch>
            <Route exact path="/" render={() => <HomePage />} />
            <Route
              path="/details"
              render={(props) => <DetailsPage {...props} />}
            />
          </Switch>
        </Container>
      </Router>
    </div>
  );
}

export default App;
