import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AdAllTheatreComp from "../../../components/AdminComp/AdAllTheatreComp";
import { URL } from "../../../config";
import { Container, Row, Col } from "reactstrap";
function AdAllTheatre() {
  const [theatres, setTheatre] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = `${URL}/theatre/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setTheatre(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      {/* <div className="container">
        <button
          onClick={() => {
            navigate("/addtheatre");
          }}
          type="button"
          class="btn btn-primary"
        >
          Add Theatre
        </button>

        <hr />

        {theatres.map((s) => {
          return <AdAllTheatreComp theatre_detail={s} />;
        })}
      </div> */}

      <Container>
        <hr />
        <button
          onClick={() => {
            navigate("/adminHome");
          }}
          type="button"
          className="btn btn-danger float-start"
        >
          {/* Admin Home */}
          Back
        </button>
        <button
          onClick={() => {
            navigate("/addtheatre");
          }}
          type="button"
          class="btn btn-primary float-end"
        >
          Add Theatre
        </button>
        <br />
        <br />
        <hr />
        <Row xs={1} md={2} style={{ marginTop: "1em" }}>
          {theatres.map((s) => {
            return <AdAllTheatreComp theatre_detail={s} />;
          })}
        </Row>
      </Container>
    </div>
  );
}

export default AdAllTheatre;
