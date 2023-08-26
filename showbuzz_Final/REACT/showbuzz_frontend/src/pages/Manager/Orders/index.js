import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Col, Container, Row, Table } from "reactstrap";
import { URL } from "../../../config";

const TheatreOrder = () => {
  const { theatreManagerId } = sessionStorage;
  const [orders, setOrders] = useState([]);
  const [movie, setMovie] = useState([]);
  const navigate = useNavigate();

  const loadAllMovies = () => {
    const url = `${URL}/movie/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovie(result["data"]);
      }
    });
  };
  var movie_name = "";
  const loadTheatreOrders = () => {
    const url = `${URL}/orders/theatre/${theatreManagerId}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setOrders(result["data"]);
      }
    });
  };
  useEffect(() => {
    loadTheatreOrders();
    loadAllMovies();
  }, []);
  return (
    <div className="container">
      <h2 style={{ textAlign: "center" }}>Theatre Orders</h2>
      <hr />

      <button
        className="btn btn-danger"
        onClick={() => {
          navigate("/managerHome");
        }}
      >
        Back
      </button>
      <hr />
      <div>
        <Container>
          <Table striped bordered hover style={{ textAlign: "center" }}>
            <Row style={{ backgroundColor: "black", color: "white" }}>
              <Col>
                <b> Payment ID</b>
              </Col>
              <Col>
                <b>Movie Name</b>
              </Col>
              <Col>
                <b>User ID</b>
              </Col>
              <Col>
                <b>Seat</b>
              </Col>
              <Col>
                <b>Booking Date</b>
              </Col>
              <Col>
                <b>Booking Time</b>
              </Col>
            </Row>
            {orders.map((o) => {
              return (
                <Row>
                  {movie.forEach((e) => {
                    if (o.movie_id == e.id) {
                      movie_name = e.movie_name;
                    }
                  })}
                  {/* {theatre.forEach((e) => {
                    if (o.theatre_id == e.theatre_id) {
                      theatre_name = e.name;
                      console.log(theatre_name);
                    }
                  })} */}
                  <Col>{o.payment_id}</Col>
                  <Col>{movie_name}</Col>
                  <Col>{o.user_id}</Col>
                  <Col>{o.seat_id}</Col>
                  <Col>{o.show_date}</Col>
                  <Col>{o.show_time}</Col>
                </Row>
              );
            })}
          </Table>
        </Container>
      </div>
    </div>
  );
};
export default TheatreOrder;
