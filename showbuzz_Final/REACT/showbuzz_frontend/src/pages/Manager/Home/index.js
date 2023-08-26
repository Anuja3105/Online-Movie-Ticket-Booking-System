import React, { useEffect } from "react";
import { URL } from "../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";
import { Col, Container, Row, Button } from "reactstrap";

const ManagerHome = () => {
  const [theatre, setTheatre] = useState([]);
  const navigate = useNavigate();
  const { id, firstName, lastName } = sessionStorage;

  const logoutUser = () => {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("firstName");
    sessionStorage.removeItem("lastName");
    sessionStorage.removeItem("theatreManagerId");
    sessionStorage.removeItem("loginStatus");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("movie_name");
    sessionStorage.removeItem("movie_id");
    sessionStorage.removeItem("movie_poster");
    sessionStorage.removeItem("booking_date");
    sessionStorage.removeItem("booking_time");
    sessionStorage.removeItem("screen_no");
    sessionStorage.removeItem("theatre_name");
    sessionStorage.removeItem("theatre_address");
    sessionStorage.removeItem("cost_factor");
    sessionStorage.removeItem("checkedSeats");
    sessionStorage.removeItem("selectedSeats");
    sessionStorage.removeItem("totalCost");

    navigate("/");
  };
  var currentDate = new Date();
  var displayDate = currentDate.toDateString();
  const showTheatreDetails = () => {
    const userId = sessionStorage.getItem("id");
    console.log(userId);
    const url = `${URL}/theatre/user/${userId}`;
    axios.get(url).then((response) => {
      const result = response.data;
      console.log(response.data);
      const { theatre_id } = result["data"];
      console.log(theatre_id);
      sessionStorage["theatreManagerId"] = theatre_id;
      if (result["status"] == "success") {
        console.log(result);
        setTheatre(result["data"]);
      } else {
        toast("error", "error");
      }
    });
  };

  const ManageScreen = () => {
    navigate("/manageScreens");
  };
  const ManageShows = () => {
    navigate("/manageShows");
  };
  useEffect(() => {
    showTheatreDetails();
  }, []);
  return (
    <div>
      <Container fluid style={{ textAlign: "center", marginTop: "20px" }}>
        <hr />
        <h2>Manager Dashboard</h2>
        <hr />
        <div className="col" style={{ margin: "2em" }}>
          <div className="float-start">
            <div className="time">{displayDate}</div>
          </div>

          <div
            className="float-start"
            style={{
              marginLeft: "2em",
              backgroundColor: "transparent",
              color: "orange",
            }}
          >
            <button
              className="btn btn-warning"
              style={{
                backgroundColor: "transparent",
                color: "orange",
              }}
              onClick={() => {
                navigate("/mOrders", { state: { theatre_id: theatre.id } });
              }}
            >
              Orders
            </button>
          </div>

          <div className="float-end">
            <div className="btn-group" role="group">
              <button
                id="btnGroupDrop1"
                type="button"
                className="btn btn-primary dropdown-toggle"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Welcome {firstName}
              </button>
              <ul className="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <li>
                  <button
                    onClick={() => {
                      navigate("/profile", { state: { id: id } });
                    }}
                    className="dropdown-item"
                  >
                    Profile
                  </button>
                </li>
                <li>
                  <button onClick={logoutUser} className="dropdown-item">
                    Logout
                  </button>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <hr />
        <Container
          style={{
            marginTop: "3em",
            backgroundColor: "lightgray",
            padding: "1em",
            boxShadow: "5px 5px 5px gray",
          }}
        >
          {" "}
          <h5 style={{ textAlign: "center" }}>Theatre Name : {theatre.name}</h5>
          <h5>Address : {theatre.address}</h5>
          <h5>
            Manager : {firstName} {lastName}
          </h5>
        </Container>
        <hr />
        <Container style={{ marginTop: "50px" }}>
          <Row>
            <Col>
              <Button
                color="danger"
                onClick={ManageScreen}
                style={{
                  backgroundColor: "transparent",
                  color: "red",
                  boxShadow: "5px 5px 5px gray",
                }}
              >
                Manage Screens
              </Button>
            </Col>
            <Col>
              <Button
                color="primary"
                onClick={ManageShows}
                style={{
                  backgroundColor: "transparent",
                  color: "blue",
                  boxShadow: "5px 5px 5px gray",
                }}
              >
                Manage Shows
              </Button>
            </Col>
            <Col>
              {/* <Button
                color="success"
                style={{
                  backgroundColor: "transparent",
                  color: "green",
                  boxShadow: "5px 5px 5px gray",
                }}
              >
                Book Ticket
              </Button> */}
            </Col>
          </Row>
        </Container>
      </Container>
    </div>
  );
};

export default ManagerHome;
