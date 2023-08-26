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

function AdAllScreenComp(props){

    const id=props.screen_detail.id;
    const theatre_id=props.screen_detail.theatre_id;
    const screen_no=props.screen_detail.screen_no;
    const showList = props.screen_detail.showList;
    
    const navigate = useNavigate();

    return(<div>
                  
        <div class="container">
        
      <div class="row row-cols-2">
          {/* <div class="col" style={styles.title}>Screen Id:</div>
          <div class="col" style={styles.subtitle}>{id}</div> */}

          <div class="col" style={styles.title}>Theatre Id:</div>
          <div class="col" style={styles.subtitle}>{theatre_id}</div>

          <div class="col" style={styles.title}>Screen No </div>
          <div class="col" style={styles.subtitle}>{screen_no}</div>


          <div class="col" style={styles.title}>Show List :</div>
          <div class="col" style={styles.subtitle}>
            <div> -------------------------  </div>
                
                   
                        {
                            showList.map((s) => {
                                return (
                                    <table>
                                        <tr>
                                            <td>Show id</td>
                                            <td>{s.id}</td>
                                        </tr>
                                        <tr>
                                            <td>Cost Factor</td>
                                            <td>{s.cost_factor}</td>
                                        </tr>
                                        <tr>
                                            <td>Movie Id</td>
                                            <td>{s.movie_id}</td>
                                        </tr>
                                        <tr>
                                            <td>Screen Id</td>
                                            <td>{s.screen_id}</td>
                                        </tr>
                                        <tr>
                                            <td>Show Date</td>
                                            <td>{s.showdate}</td>
                                        </tr>
                                        <tr>
                                            <td>Show Time</td>
                                            <td>{s.showtime}</td>
                                        </tr>
                                    </table>
                        
                                )
                            })
                        }
                    
               
                <div> -------------------------  </div>
          </div>

          
          
          
          
          <div class="col" style={styles.title}><button onClick={() => {
                  navigate('/editscreen',{state:{id:id}})
              }} type="button" class="btn btn-success">Edit</button></div>


          <div class="col" style={styles.title}><button onClick={() => {
              const url = "http://localhost:8080/screen/"+id;
              axios.delete(url).then((response) => {
                const result = response.data;
                if(result['status'] == 'success'){
                  console.log(result['data'])
                  
                }else{
                  console.log(result['error'])
                }
                navigate('/adallcreen');
              })
          }} type="button" class="btn btn-danger">Delete</button></div>

          
          
      </div>  
      <hr />   
  </div>
 


</div>);
}



export default AdAllScreenComp;