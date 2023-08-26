import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AdAllCityComp from "../../../components/AdminComp/AdAllCityComp";
import { URL } from "../../../config";
import { Container, Row, Col } from "reactstrap";

function AdAllCity() {
  const [cities, setCities] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = `${URL}/city/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setCities(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      {/* <div class="container">
        <button
          onClick={() => {
            navigate("/addcity");
          }}
          type="button"
          class="btn btn-primary"
        >
          Add City
        </button>

        <hr />

        {cities.map((s) => {
          return <AdAllCityComp city_detail={s} />;
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
            navigate("/addcity");
          }}
          type="button"
          class="btn btn-primary float-end"
        >
          Add City
        </button>
        <br />
        <br />
        <hr />
        <Row xs={1} md={4} style={{ marginTop: "1em" }}>
          {cities.map((s) => {
            return <AdAllCityComp city_detail={s} />;
          })}
        </Row>
      </Container>
    </div>
  );
}

export default AdAllCity;
