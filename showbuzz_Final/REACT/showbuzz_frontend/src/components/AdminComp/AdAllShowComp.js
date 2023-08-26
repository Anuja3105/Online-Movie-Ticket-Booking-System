import axios from "axios";
import { useNavigate } from "react-router";

const styles = {
    title: {
      fontSize: '1.3em',
      fontWeight: 'bold',
    },
    subtitle: {
      color: 'darkgray',
    },
    detailsContainer: {
      marginTop: '10px',
      marginBottom: '10px',
    },
  }

function AdAllShowComp(props){

  const { show_detail } = props;

    const id=props.show_detail.id;
    const movie_id=props.show_detail.movie_id;
    const screen_id=props.show_detail.screen_id;
    const cost_factor=props.show_detail.cost_factor;
    const showdate=props.show_detail.showdate;
    const showtime=props.show_detail.showtime;

    const navigate = useNavigate();

    return(<div>
                  
        <div class="container">
        
      <div class="row row-cols-2">
          <div class="col" style={styles.title}>Movie Id:</div>
          <div class="col" style={styles.subtitle}>{movie_id}</div>
          
          <div class="col" style={styles.title}>Screen Id:</div>
          <div class="col" style={styles.subtitle}>{screen_id}</div>
          
          <div class="col" style={styles.title}>Cost Factor:</div>
          <div class="col" style={styles.subtitle}>{cost_factor}</div>
          
          <div class="col" style={styles.title}>Show Date:</div>
          <div class="col" style={styles.subtitle}>{showdate}</div>
          
          <div class="col" style={styles.title}>Show Time:</div>
          <div class="col" style={styles.subtitle}>{showtime}</div>
          
          
          
          
          
          
          <div class="col" style={styles.title}><button onClick={() => {
                  navigate('/editshow',{state:{show_detail:show_detail}})
              }} type="button" class="btn btn-success">Edit</button></div>


          <div class="col" style={styles.title}><button onClick={() => {
              const url = "http://localhost:8080/show/"+id;
              axios.delete(url).then((response) => {
                const result = response.data;
                if(result['status'] == 'success'){
                  console.log(result['data'])
                  
                }else{
                  console.log(result['error'])
                }
                navigate('/adallshow');
              })
          }} type="button" class="btn btn-danger">Delete</button></div>

          
          
      </div>  
      <hr />   
  </div>
 


</div>);

}

export default AdAllShowComp;