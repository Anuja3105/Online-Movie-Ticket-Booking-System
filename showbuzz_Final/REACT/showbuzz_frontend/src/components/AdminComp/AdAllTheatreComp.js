import axios from "axios";
import { Alert } from "bootstrap";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
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

const deletedPopUp = () => {
  alert("Do you really want to delete this theatre?");
};
function AdAllTheatreComp(props) {
  const navigate = useNavigate();
  const [city, setCity] = useState({});
  const [user, setUser] = useState({});

  const id = props.theatre_detail.theatre_id;
  const name = props.theatre_detail.name;
  const city_id = props.theatre_detail.city_id;
  const address = props.theatre_detail.address;
  const no_of_screen = props.theatre_detail.no_of_screen;
  const key1 = props.theatre_detail.key1;
  const key2 = props.theatre_detail.key2;
  const user_id = props.theatre_detail.manager_id;
  const theatre = props.theatre_detail;
  useEffect(() => {
    const url1 = `${URL}/city/find/${city_id}`;
    axios.get(url1).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setCity(result["data"]);
      } else {
      }
      navigate("/adalltheatre");
    });

    const url2 = `${URL}/user/${user_id}`;
    axios.get(url2).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setUser(result["data"]);
      } else {
      }
      navigate("/adalltheatre");
    });
  }, []);

  return (
    <div>
      <div class="container">
        <div class="row row-cols-2 shadow p-3 mb-5 bg-body rounded">
          <div class="col" style={styles.title}>
            Name:
          </div>
          <div class="col" style={styles.subtitle}>
            {name}
          </div>

          <div class="col" style={styles.title}>
            City Details :
          </div>
          <div class="col" style={styles.subtitle}>
            <table>
              <tr>
                <td>Name :</td>
                <td>{city.city_name}</td>
              </tr>

              <tr>
                <td>State :</td>
                <td>{city.city_state}</td>
              </tr>

              <tr>
                <td>Pincode :</td>
                <td>{city.city_pincode}</td>
              </tr>
            </table>
          </div>

          <div class="col" style={styles.title}></div>
          <div class="col" style={styles.title}>
            <hr />
          </div>

          <div class="col" style={styles.title}>
            Manager Details:{" "}
          </div>
          <div class="col" style={styles.subtitle}>
            <table>
              <tr>
                <td>Name :</td>
                <td>
                  {user.first_name} {user.last_name}
                </td>
              </tr>

              <tr>
                <td>Email :</td>
                <td>{user.email}</td>
              </tr>
            </table>
          </div>

          <div class="col" style={styles.title}></div>
          <div class="col" style={styles.title}>
            <hr />
          </div>

          <div class="col" style={styles.title}>
            Address:
          </div>
          <div class="col" style={styles.subtitle}>
            {address}
          </div>

          <div class="col" style={styles.title}>
            No Of Screen:
          </div>
          <div class="col" style={styles.subtitle}>
            {no_of_screen}
          </div>

          <div class="col" style={styles.title}>
            Key Id:
          </div>
          <div class="col" style={styles.subtitle}>
            {key1}
          </div>

          <div class="col" style={styles.title}>
            Secret Key:
          </div>
          <div class="col" style={styles.subtitle}>
            {key2}
          </div>

          <br />
          <div class="col" style={styles.title}>
            <hr />
            <button
              onClick={() => {
                navigate("/edittheatre", {
                  state: { theatre: theatre },
                });
              }}
              type="button"
              class="btn btn-success"
            >
              Edit
            </button>
          </div>

          <div class="col" style={styles.title}>
            <hr />
            <button
              onClick={() => {
                deletedPopUp();
                const url = `${URL}/theatre/delete/${id}`;
                axios.delete(url).then((response) => {
                  const result = response.data;
                  if (result["status"] == "success") {
                    swal("Theatre deleted successfully....!!!", "");
                  } else {
                    toast.warning(result["error"]);
                  }
                  navigate("/adalltheatre");
                });
              }}
              type="button"
              class="btn btn-danger float-end"
            >
              Delete
            </button>
          </div>
        </div>
        <hr />
      </div>
    </div>
  );
}

export default AdAllTheatreComp;
