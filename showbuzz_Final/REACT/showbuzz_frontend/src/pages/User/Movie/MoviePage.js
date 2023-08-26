import { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router";
import axios from "axios";
import Movie from "../../../components/UserComp/Movie";
import Rating from "../../../components/UserComp/Rating";
import { URL } from "../../../config";
import "./MoviePage.css";

function MoviePage() {
  const navigate = useNavigate();
  const { state } = useLocation();
  const movie_id = state.id;
  const [movie, setMovie] = useState({});

  const [show, setShow] = useState(false);

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  useEffect(() => {
    const url = `${URL}/movie/find/` + movie_id;
    // const url1 = `${URL}/movie/find/1`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovie(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      <Movie movie_detail={movie} />
      <div className="row">
        <div className="col">
          <div
            onClick={() => {
              navigate("/addrating", { state: { id: movie.id } });
            }}
            type="button"
            className="btn rating "
          >
            Add Rating
          </div>
          <div
            onClick={() => {
              navigate("/review", { state: { id: movie.id } });
            }}
            type="button"
            className="btn  review"
          >
            Movie Review
          </div>
        </div>
      </div>
    </div>
  );
}

export default MoviePage;
