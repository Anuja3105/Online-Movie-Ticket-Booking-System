import { useState, useEffect } from "react";
import { Container, Button, Label } from "reactstrap";
import axios from "axios";
import { useNavigate } from "react-router";
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

function Movie(props) {
  const { movie_detail } = props;
  const [rating, setRating] = useState({});
  const navigate = useNavigate();
  const movie_id = props.movie_detail.id;
  const movie_name = props.movie_detail.movie_name;
  const movie_details = props.movie_detail.movie_details;
  const category = props.movie_detail.category;
  const language = props.movie_detail.language;
  const release_date = props.movie_detail.release_date;
  const poster = props.movie_detail.movie_poster;
  const genre = props.movie_detail.genre;

  useEffect(() => {
    const url = `${URL}/rating/1/aggregate`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setRating(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  console.log(rating.avg);
  return (
    <div>
      {/* <div className="row">
        <div className="col"></div>
        <div className="col"> */}
      {/* <div className="row"></div> */}
      {/* <table>
            <tr>
              <td style={styles.title}>Name :</td>
              <td style={styles.subtitle}>{movie_name}</td>
            </tr>
            <tr>
              <td style={styles.title}>Details :</td>
              <td style={styles.subtitle}>{movie_details}</td>
            </tr>
            <tr>
              <td style={styles.title}>Category:</td>
              <td style={styles.subtitle}>{category}</td>
            </tr>
            <tr>
              <td style={styles.title}>Language :</td>
              <td style={styles.subtitle}>{language}</td>
            </tr>
            <tr>
              <td style={styles.title}>ReleaseDate:- </td>
              <td style={styles.subtitle}>{release_date}</td>
            </tr>

            <tr>
              <td style={styles.title}>Poster :</td>
              <td>
                <img
                  src={movie_detail.moviePoster}
                  className="img-fluid img-thumbnail"
                  alt="movie poster"
                  width={200}
                />
              </td>
            </tr>
            <tr>
              <td style={styles.title}>Genre :</td>
              <td style={styles.subtitle}>{genre}</td>
            </tr>
          </table> */}
      {/* ****************************************************************************** */}

      {/* </div>
        <div className="col"></div>
      </div> */}
      <Container fluid style={{ marginTop: "20px" }}>
        <div className="card mb-3 shadow p-3 mb-5 bg-white rounded">
          <div className="row g-0">
            <div className="col-md-3 shadow-sm p-3 mb-5 bg-white rounded">
              <img
                src={movie_detail.moviePoster}
                className="img-fluid img-thumbnail rounded mx-auto d-block "
                alt="movie poster"
                width={250}
              />
            </div>
            <div className="col-md-9">
              <div
                class="movie review"
                style={{
                  textAlign: "center",
                  width: "30em",
                  height: "auto",
                  fontSize: "30px",
                  fontWeight: "bold",
                }}
              >
                Movie Details
              </div>
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
                            <b>Rating : </b>
                          </Label>
                        </td>
                        <td>{rating.avg}</td>
                      </tr>
                      <tr>
                        <td style={{ width: "15%" }}>
                          <Label className="card-title">
                            <b>About : </b>
                          </Label>
                        </td>
                        <td>{movie_details}</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>

        <hr />
      </Container>
    </div>
  );
}

export default Movie;
