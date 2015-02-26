package beans;

public class ReviewBean {
private String comments;
private String reviewedBy;
private int rating;

public String getComments() {
	return comments;
}

public void setComments(String comments) {
	this.comments = comments;
}

public String getReviewedBy() {
	return reviewedBy;
}

public void setReviewedBy(String reviewedBy) {
	this.reviewedBy = reviewedBy;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

}
