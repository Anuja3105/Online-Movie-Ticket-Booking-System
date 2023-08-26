import { useNavigate } from "react-router";
import "../UserComp/AllMovieComp.css";

const styles = {
  title: {
    fontSize: "1.3em",
    fontWeight: "bold",
    textAlign: "center",
  },
  subtitle: {
    color: "darkgray",
  },
  detailsContainer: {
    marginTop: "10px",
    marginBottom: "10px",
  },
  divcolor: {
    color: "green",
  },
};

function AllMovieComp(props) {
  const { movie_detail } = props;

  const id = props.movie_detail.id;
  const movie_name = props.movie_detail.movie_name;
  const movie_details = props.movie_detail.movie_details;

  const navigate = useNavigate();

  return (
    <div>
      <div className="row">
        <div className="col">
          <div class="container">
            <div
              className="card shadow p-3 mb-5 bg-body rounded"
              style={{ width: "20rem", height: "35rem" }}
            >
              <img
                src={movie_detail.moviePoster}
                class="card-img-top"
                alt="movie poster"
                type="button"
                onClick={() => {
                  navigate("/movie", { state: { id: id } });
                }}
              ></img>

              <div className="card-body">
                <div class="col" style={styles.title}>
                  {movie_name}
                </div>

                <div
                  class="col"
                  style={(styles.subtitle, { textAlign: "justify" })}
                >
                  <p className="card-text" maxLength="50">
                    {movie_details.length > 50
                      ? movie_details.substring(0, 50) + "..."
                      : movie_details}
                  </p>
                </div>
              </div>
            </div>
            <div class="container"></div>
          </div>

           <div class="container">
            <div class="row row-cols-2 shadow p-3 mb-5 bg-body rounded">
              <div class="col" style={styles.title}>
                Name:
              </div>
              <div class="col" style={styles.title}>
                {movie_name}
              </div>
              <div class="col" style={styles.title}>
                Details:
                <img
                  src={movie_detail.moviePoster}
                  className="img-fluid img-thumbnail"
                  alt="movie poster"
                />
              </div>
              <div class="col" style={styles.subtitle}>
                {movie_details}
              </div>
              <div class="col" style={styles.title}></div>
              <div class="col" style={styles.subtitle}>
                <button
                  onClick={() => {
                    navigate("/movie", { state: { id: id } });
                  }}
                  type="button"
                  class="btn btn-primary"
                >
                  SELECT
                </button>
              </div>
            </div>
            <div class="container"></div>
          </div> 

          <div></div>
          <hr />
        </div>
      </div>
    </div>
  );
}

export default AllMovieComp;
