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

function Review(props) {
  const firstName = props.review.firstName;
  const lastName = props.review.lastName;
  const review = props.review.review;

  return (
    <div>
      <div className="shadow-sm p-3 mb-5 bg-body rounded">
        <div className="row">
          <div className="col ">
            <br />
            <div style={styles.subtitle}>
              Created By {firstName} {lastName}
            </div>
          </div>
        </div>

        <div className="row">
          <div className="col">
            <div style={styles.detailsContainer}> :- {review}</div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Review;
