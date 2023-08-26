import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { URL } from "../../../config";
import { Container, Row, Col, Table } from "reactstrap";
const MyOrders = () => {
  const { state } = useLocation();
  const [orders, setOrders] = useState([]);
  const [movie, setMovie] = useState([]);
  const [theatre, setTheatre] = useState([]);
  const userId = sessionStorage["id"];
  const navigate = useNavigate();
  // console.log(userId);
  const { city_id } = sessionStorage;

  const loadUserOrders = () => {
    const url = `${URL}/orders/user/${userId}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setOrders(result["data"]);
      }
    });
  };

  const loadAllMovies = () => {
    const url = `${URL}/movie/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovie(result["data"]);
      }
    });
  };
  const loadAllTheatres = () => {
    const url = `${URL}/theatre/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setTheatre(result["data"]);
      }
    });
  };
  useEffect(() => {
    loadUserOrders();
    loadAllMovies();
    loadAllTheatres();
  }, []);
  console.log(orders);
  console.log(theatre);
  console.log(movie);
  var movie_name = "";
  var theatre_name = "";
  //    "order_id": 6,
  //             "user_id": 9,
  //             "payment_id": 17,
  //             "movie_id": 8,
  //             "theatre_id": 35,
  //             "screen_id": 26,
  //             "show_id": 27,
  //             "booking_id": 0,
  //             "seat_id": "C2",
  //             "show_date": "2022-04-30",
  //             "show_time": "06:00:00"
  return (
    <div>
      <h2 style={{ textAlign: "center" }}>My Orders</h2>
      <hr />
      <hr />
      <button
        className="btn btn-danger"
        onClick={() => {
          navigate("/home", { state: { id: city_id } });
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
                <b>Theatre Name</b>
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
                  {theatre.forEach((e) => {
                    if (o.theatre_id == e.theatre_id) {
                      theatre_name = e.name;
                      console.log(theatre_name);
                    }
                  })}
                  <Col>{o.payment_id}</Col>
                  <Col>{movie_name}</Col>
                  <Col>{theatre_name}</Col>
                  <Col>{o.seat_id}</Col>
                  <Col>{o.show_date}</Col>
                  <Col>{o.show_time}</Col>
                </Row>
              );
            })}
          </Table>
        </Container>
      </div>

      {/* <Container>
          <Table striped>
            <Row>
              <Col>Screen ID</Col>
              <Col>Screen No</Col>
              <Col>Edit</Col>
              <Col>Delete</Col>
            </Row>
            {screensList.map((screen) => {
              return (
                <Row className="shadow p-3 mb-5 bg-body rounded">
                  <Col>{screen.id}</Col>
                  <Col>{screen.screen_no}</Col>
                  <Col>
                    {console.log(screen.id)}
                    <Button
                      color="primary"
                      onClick={() =>
                        navigate("/MEditScreen", { state: { id: screen.id } })
                      }
                      style={{
                        marginTop: "10px",
                        marginBottom: "10px",
                        color: "black",
                      }}
                    >
                      Edit
                    </Button>
                  </Col>
                  <Col>
                    <Button
                      color="danger"
                      onClick={() => {
                        const delurl = `${URL}/screen/${screen.id}`;
                        axios.delete(delurl).then((response1) => {
                          const res = response1.data;
                          if (res["status"] == "success") {
                            window.location.reload();
                            toast.success("Screen Deleted");
                          } else {
                            toast.error("Screen Not Deleted");
                          }
                        });
                      }}
                      style={{
                        marginTop: "10px",
                        marginBottom: "10px",
                        color: "black",
                      }}
                    >
                      Delete
                    </Button>
                  </Col>
                </Row>
              );
            })}
          </Table>
        </Container> */}
    </div>
  );
};
export default MyOrders;
