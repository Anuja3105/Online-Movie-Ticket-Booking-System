import axios from "axios";
import { useNavigate } from "react-router";
import swal from "sweetalert";
import { URL } from "../.././config";
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
  alert("Do you want to delete this city?");
};
function AdAllCityComp(props) {
  const { city_detail } = props;

  const id = props.city_detail.city_id;
  const city_name = props.city_detail.city_name;
  const city_state = props.city_detail.city_state;
  const city_pincode = props.city_detail.city_pincode;
  const city_country = props.city_detail.city_country;
  const navigate = useNavigate();

  return (
    <div>
      {/* <div class="container">
        <div class="row row-cols-2">
          <div class="col" style={styles.title}>
            Name:
          </div>
          <div class="col" style={styles.subtitle}>
            {city_name}
          </div>
          <div class="col" style={styles.title}>
            State:
          </div>
          <div class="col" style={styles.subtitle}>
            {city_state}
          </div>
          <div class="col" style={styles.title}>
            Pincode
          </div>
          <div class="col" style={styles.subtitle}>
            {city_pincode}
          </div>

          <div class="col" style={styles.title}>
            <button
              onClick={() => {
                navigate("/editcity", { state: { city_detail: city_detail } });
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
                const url = "http://localhost:8080/city/" + id;
                axios.delete(url).then((response) => {
                  const result = response.data;
                  if (result["status"] == "success") {
                    console.log(result["data"]);
                  } else {
                    console.log(result["error"]);
                  }
                  navigate("/adallcity");
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
      <div
        className="card text-dark bg-light mb-3 shadow p-3 mb-5 bg-white rounded"
        style={{ maxWidth: "18rem" }}
      >
        <div className="card-body">
          <h5 className="card-title">{city_name}</h5>
          <hr />
          <p className="card-text">
            <b> State: </b> {city_state}
          </p>
          <p className="card-text">
            <b> Pincode:</b> {city_pincode}
          </p>
          <hr />
          <div class="col">
            <button
              onClick={() => {
                navigate("/editcity", { state: { city_detail: city_detail } });
              }}
              type="button"
              class="btn btn-success float-start"
              style={{
                backgroundColor: "transparent",
                color: "green",
              }}
            >
              Edit
            </button>

            <button
              onClick={() => {
                deletePopUp();
                const url = `${URL}/city/${id}`;
                axios.delete(url).then((response) => {
                  const result = response.data;
                  if (result["status"] == "success") {
                    console.log(result["data"]);
                    swal("City deleted successfully...!!!", "success");
                  } else {
                    swal("Error...!!!", "error");
                    console.log(result["error"]);
                  }
                  navigate("/adallcity");
                });
              }}
              type="button"
              class="btn btn-danger float-end"
              style={{
                backgroundColor: "transparent",
                color: "red",
              }}
            >
              Delete
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdAllCityComp;
