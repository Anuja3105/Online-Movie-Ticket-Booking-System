import axios from "axios";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { Container, Button, Row, Col, Label } from "reactstrap";
import swal from "sweetalert";
import { URL } from "../../config";

const styles = {
  title: {
    fontSize: "1.3em",
    fontWeight: "bold",
  },
  subtitle: {
    color: "darkgray",
  },
  detailsContainer: {
    marginTop: "10px",
    marginBottom: "10px",
  },
};
const deletePopUp = () => {
  alert("Do you want to delete this show?");
};
function AdAllMovieComp(props) {
  const { movie_detail } = props;

  const id = props.movie_detail.id;
  const movie_name = props.movie_detail.movie_name;
  const movie_details = props.movie_detail.movie_details;
  const category = props.movie_detail.category;
  const language = props.movie_detail.language;
  const release_date = props.movie_detail.release_date;
  const poster = props.movie_detail.movie_poster;
  const genre = props.movie_detail.genre;
  const navigate = useNavigate();

  return (
    <div>
      {/* <div class="container">
        <div class="row row-cols-2">
          <div class="col" style={styles.title}>
            Name:
          </div>
          <div class="col" style={styles.subtitle}>
            {movie_name}
          </div>
          <div class="col" style={styles.title}>
            Details:
          </div>
          <div class="col" style={styles.subtitle}>
            {movie_details}
          </div>
          <div class="col" style={styles.title}>
            Poster
          </div>

          <div class="col" style={styles.title}>
            <img
              src={movie_detail.moviePoster}
              className="img-fluid img-thumbnail"
              alt="movie poster"
              width={150}
            />
          </div>

          <div class="col" style={styles.title}>
            <button
              onClick={() => {
                navigate("/editmovie", { state: { id: id } });
              }}
              type="button"
              class="btn btn-success"
            >
              Edit
            </button>
          </div>

          <div class="col" style={styles.title}>
            <button
              onClick={() => {
                const url = "http://localhost:8080/movie/" + id;
                axios.delete(url).then((response) => {
                  const result = response.data;
                  if (result["status"] == "success") {
                    console.log(result["data"]);
                  } else {
                    console.log(result["error"]);
                  }
                  navigate("/adallmovie");
                });
              }}
              type="button"
              class="btn btn-danger"
            >
              Delete
            </button>
          </div>
        </div>
        <hr />
      </div> */}

      <Container fluid style={{ marginTop: "20px" }}>
        <div className="card mb-3 shadow p-3 mb-5 bg-white rounded">
          <div className="row g-0">
            <div className="col-md-3 shadow-sm p-3 mb-5 bg-white rounded">
              <img
                src={movie_detail.moviePoster}
                className="img-fluid img-thumbnail rounded mx-auto d-block "
                alt="movie poster"
                width={200}
              />
            </div>
            <div className="col-md-9">
              <div className="card-body">
                <div className="col">
                  <table>
                    <tbody>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label
                            className="card-title"
                            style={{ fontWeight: "10px" }}
                          >
                            <b>Name :</b>
                          </Label>
                        </td>
                        <td>
                          <b>{movie_name}</b>
                        </td>
                      </tr>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>Genre : </b>
                          </Label>
                        </td>
                        <td>{genre}</td>
                      </tr>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>Release Date : </b>
                          </Label>
                        </td>
                        <td>{release_date}</td>
                      </tr>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>Category : </b>
                          </Label>
                        </td>
                        <td>{category}</td>
                      </tr>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>Language : </b>
                          </Label>
                        </td>
                        <td>{language}</td>
                      </tr>

                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>About : </b>
                          </Label>
                        </td>
                        <td>
                          {movie_details.length > 200
                            ? movie_details.substring(0, 200) + "..."
                            : movie_details}
                        </td>
                      </tr>
                      <tr>
                        <td colSpan={2}>
                          <hr />
                          <div class="col" style={styles.title}>
                            <button
                              onClick={() => {
                                navigate("/editmovie", {
                                  state: { movie: movie_detail },
                                });
                              }}
                              type="button"
                              class="btn btn-success float-start"
                            >
                              Edit
                            </button>

                            <button
                              onClick={() => {
                                const url = `${URL}/movie/${id}`;
                                axios.delete(url).then((response) => {
                                  deletePopUp();
                                  const result = response.data;
                                  if (result["status"] == "success") {
                                    console.log(result["data"]);
                                    navigate("/adallmovie");
                                    swal(
                                      "Movie deleted successfully...!!!",
                                      ""
                                    );
                                  } else {
                                    toast.warning("Error...!!!");
                                  }
                                });
                              }}
                              type="button"
                              class="btn btn-danger float-end"
                            >
                              Delete
                            </button>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </Container>
    </div>
  );
}

export default AdAllMovieComp;
