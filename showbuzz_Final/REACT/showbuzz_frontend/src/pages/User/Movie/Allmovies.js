import axios from "axios";
import { useEffect, useState } from "react";
import AllMovieComp from "../../../components/UserComp/AllMovieComp";
import { URL } from "../../../config";
import { Container, Row, Col } from "reactstrap";
import { useNavigate } from "react-router-dom";

function AllMovies() {
  const [movies, setMovies] = useState([]);
  const navigate = useNavigate();
  const { city_id } = sessionStorage;
  useEffect(() => {
    const url = `${URL}/movie/all`;
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
    // <div className="grid">
    //   {movies.map((s) => {
    //     return <AllMovieComp movie_detail={s} />;
    //   })}
    // </div>
    <Container>
      <h2 style={{ textAlign: "center" }}>All Movies</h2>
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
      <Row xs={1} md={3} style={{ marginTop: "1em" }}>
        {movies.map((s) => {
          console.log("s====",s);
          return <AllMovieComp movie_detail={s} />;
        })}
      </Row>
    </Container>
  );
}

export default AllMovies;
