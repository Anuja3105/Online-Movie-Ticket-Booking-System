import React, { useEffect } from "react";
import { URL } from "../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router";
import { Col, Container, Row, Button, Table } from "reactstrap";
import swal from "sweetalert";

const ManageScreens = () => {
  const { state } = useLocation();
  const [screensList, SetScreens] = useState([]);
  const showSceens = () => {
    const theatre_id = sessionStorage.getItem("theatreManagerId");
    console.log(theatre_id);
    const url = `${URL}/screen/${theatre_id}`;
    axios.get(url).then((response) => {
      const rseult = response.data;
      if (rseult["status"] == "success") {
        console.log(rseult);
        SetScreens(rseult["data"]);
      }
    });
  };
  const deletePopUp = () => {
    alert("Do you want to delete this screen?");
  };
  const navigate = useNavigate();
  const Back = () => {
    navigate("/managerHome");
  };
  useEffect(() => {
    showSceens();
  }, []);

  return (
    <div>
      <Container style={{ textAlign: "center", marginTop: "30px" }}>
        <h2>Manage Screens</h2>{" "}
        <Button color="danger" onClick={Back} style={{ float: "left" }}>
          Back
        </Button>
        <br />
        <hr />
        <Button color="success" onClick={() => navigate("/MAddScreen")}>
          Add New Screen
        </Button>
        <hr />
        <Container>
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
                        deletePopUp();
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
                        swal("Screen deleted successfully...!!!");
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
        </Container>
      </Container>
    </div>
  );
};
export default ManageScreens;
