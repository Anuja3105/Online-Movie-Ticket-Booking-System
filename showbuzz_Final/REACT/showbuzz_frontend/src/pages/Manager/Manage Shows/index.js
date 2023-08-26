import React, { useEffect } from "react";
import { URL } from "../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router";
import { Col, Container, Row, Button, Table } from "reactstrap";
import swal from "sweetalert";

const MManageShows = () => {
  const { state } = useLocation();
  const [showList, SetShows] = useState([]);
  const [movie, setMovie] = useState([]);

  const loadAllMovies = () => {
    const url = `${URL}/movie/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovie(result["data"]);
      }
    });
  };

  const deletePopUp = () => {
    alert("Do you want to delete this show?");
  };
  var movie_name = "";

  const showShows = () => {
    const theatre_id = sessionStorage.getItem("theatreManagerId");
    console.log(theatre_id);

    const url = `${URL}/show/theatre/${theatre_id}`;
    axios.get(url).then((response) => {
      const rseult = response.data;
      if (rseult["status"] == "success") {
        console.log(rseult);
        SetShows(rseult["data"]);
      }
    });
  };
  const navigate = useNavigate();
  const Back = () => {
    navigate("/managerHome");
  };
  useEffect(() => {
    showShows();
    loadAllMovies();
  }, []);

  return (
    <div>
      <Container style={{ textAlign: "center", marginTop: "30px" }}>
        <h2>Manage Shows</h2>{" "}
        <Button color="danger" onClick={Back} style={{ float: "left" }}>
          Back
        </Button>
        <br />
        <hr />
        <Button color="success" onClick={() => navigate("/mAddShow")}>
          Add New Show
        </Button>
        <hr />
        <Container>
          <Table striped>
            <Row>
              <Col>Show ID</Col>
              <Col>Moive </Col>
              <Col>Screen ID</Col>
              <Col>Cost Factor</Col>
              <Col>Show Date</Col>
              <Col>Show Time</Col>
              <Col>Edit</Col>
              <Col>Delete</Col>
              <Col>Booking Status</Col>
            </Row>
            {showList.map((show) => {
              return (
                <Row>
                  {movie.forEach((e) => {
                    if (show.movie_id == e.id) {
                      movie_name = e.movie_name;
                    }
                  })}
                  <Col>{show.id}</Col>
                  <Col>{movie_name}</Col>
                  <Col>{show.screen_id}</Col>
                  <Col>{show.cost_factor}</Col>
                  <Col>{show.showdate}</Col>
                  <Col>{show.showtime}</Col>
                  <Col>
                    {console.log(show.id)}
                    <Button
                      color="primary"
                      onClick={() =>
                        navigate("/mEditShow", { state: { show: show } })
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
                        deletePopUp();
                        const delurl = `${URL}/show/${show.id}`;
                        axios.delete(delurl).then((response1) => {
                          const res = response1.data;
                          if (res["status"] == "success") {
                            window.location.reload();
                            // toast.success("Show Deleted");
                            swal("Show deleted successfully..!!!");
                          } else {
                            toast.error("Show Not Deleted");
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
                  <Col>
                    <button
                      className="btn btn-success"
                      onClick={() => {
                        navigate("/mBooking", { state: { show_id: show.id } });
                      }}
                      style={{ color: "black" }}
                    >
                      Booking Status
                    </button>
                  </Col>
                </Row>
              );
            })}
          </Table>
        </Container>
      </Container>
    </div>
  );
};
export default MManageShows;
