import "bootstrap/dist/css/bootstrap.min.css";
import { Row, Col, Container } from "reactstrap";
import React from "react";
import { URL } from "../../../config";
import axios from "axios";
import { useState, useEffect } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";
const TicketShow = () => {
  var theatrename = sessionStorage.getItem("theatre_name");
  var theatre_address = sessionStorage.getItem("theatre_address");
  var screeNo = sessionStorage.getItem("screen_no");
  var selectedSeats = sessionStorage.getItem("selectedSeats");
  var totalCost = sessionStorage.getItem("totalCost");
  var cityName = sessionStorage.getItem("city_name");
  var showTime = sessionStorage.getItem("booking_time");
  var showDate = sessionStorage.getItem("booking_date");

  const navigate = useNavigate();
  const { id, bookingId, payment_id, show_id } = sessionStorage;
  const [paymentID, setPaymentID] = useState();

  var bookings = [];
  bookings = bookingId.split(",");
  console.log(bookings);

  const seats = selectedSeats.split(",");
  const payment = () => {
    const url = `${URL}/payment/id/${payment_id}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setPaymentID(result["data"]);
      }
    });
  };

  console.log(paymentID);
  console.log(bookings[0]);
  console.log(seats[0]);
  const insertOrder = () => {
    for (let i = 0; i < bookings.length; i++) {
      const body = {
        user_id: id,
        payment_id: paymentID,
        show_id,
        booking_id: bookings[i],
        seat_id: seats[i],
      };
      const url = `${URL}/orders`;
      axios.post(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          console.log("Order Inserted");
        }
      });
    }
  };
  // console.log(paymentID);
  useEffect(() => {
    payment();
  }, []);

  // const newDate = new Date();
  return (
    <Container>
      <h2 style={{ textAlign: "center", marginTop: "20px" }}>
        Booking Details
      </h2>
      <hr />
      <button
        onClick={() => {
          sessionStorage.removeItem("id");
          sessionStorage.removeItem("firstName");
          sessionStorage.removeItem("lastName");
          sessionStorage.removeItem("loginStatus");
          sessionStorage.removeItem("role");

          navigate("/");
        }}
        className="btn btn-danger"
        style={{ backgroundColor: "transparent", color: "red", width: "10em" }}
      >
        Logout
      </button>
      <Container
        // className="shadow p-3 mb-5 bg-body rounded"
        style={{
          backgroundColor: "lightcyan",
          border: "2px solid",
          borderColor: "black",
          height: "350px",
          width: "700px",
          marginTop: "20px",
          boxShadow: "5px 5px 4px gray",
        }}
      >
        <div style={{ textAlign: "center" }}>
          <h2>Ticket</h2>
          <hr />
          <h3>{sessionStorage.getItem("movie_name").toUpperCase()}</h3>
          <hr />
        </div>
        <Container>
          <div className="row">
            <div
              className="col-6"
              style={{ borderRight: "2px dashed", borderColor: "black" }}
            >
              <Row>
                Theatre Name :- {theatrename},{theatre_address},{cityName}
              </Row>
              <Row>Seat No :- {selectedSeats}</Row>
              <Row>Screen No:- {screeNo}</Row>
            </div>
            <div className="col-6">
              <Container>
                <Row>Date: {showDate}</Row>
                <Row>Show Time :- {showTime}</Row>
                <Row>Total Amount :- {totalCost}.00 Rs</Row>
              </Container>
            </div>
          </div>
        </Container>
        <Container style={{ marginTop: "30px" }}>
          <Row>
            <Col>
              <h6>
                <b>Booking Id :- </b> {sessionStorage.getItem("order_id")}
              </h6>{" "}
              <h6>
                <b>Payment Id :-</b> {sessionStorage.getItem("payment_id")}
              </h6>
              <h6>
                <b>Booking Date and Time :-</b>{" "}
                {sessionStorage.getItem("datetime")}
              </h6>
            </Col>
          </Row>
        </Container>
      </Container>
      <br />
      <button
        className="btn btn-primary btn-lg btn-block float-end"
        onClick={() => {
          for (let i = 0; i < bookings.length; i++) {
            const body = {
              user_id: id,
              payment_id: paymentID,
              show_id,
              booking_id: bookings[i],
              seat_id: seats[i],
            };
            const url = `${URL}/orders`;
            axios.post(url, body).then((response) => {
              const result = response.data;
              if (result["status"] == "success") {
                console.log("Order Inserted");
              }
            });
          }
          navigate("/thankYou");
        }}
        style={{ alignContent: "center" }}
      >
        Next
      </button>
      <br />
      <br />
      <hr />
    </Container>
  );
};

export default TicketShow;
