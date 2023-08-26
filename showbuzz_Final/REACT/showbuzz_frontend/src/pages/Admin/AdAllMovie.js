import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AdAllMovieComp from "../../components/AdminComp/AdAllMovieComp";
import { Container, Row, Col, Button } from "reactstrap";
function AdAllMovie() {
  const [movies, setMovies] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = "http://localhost:8080/movie/all";
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovies(result["data"]);
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
            navigate("/addmovie");
          }}
          type="button"
          class="btn btn-primary"
        >
          Add Movie
        </button>

        <hr />
        {movies.map((s) => {
          return <AdAllMovieComp movie_detail={s} />;
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
          Back
        </button>
        <button
          onClick={() => {
            navigate("/addmovie");
          }}
          type="button"
          class="btn btn-primary float-end"
        >
          Add Movie
        </button>
        <br/>
        <br/>
        <hr/>
        <Row xs={1} md={1} style={{ marginTop: "1em" }}>
          {movies.map((s) => {
            return <AdAllMovieComp movie_detail={s} />;
          })}
        </Row>
      </Container>
    </div>
  );
}

export default AdAllMovie;
