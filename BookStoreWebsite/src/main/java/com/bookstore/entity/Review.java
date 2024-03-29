package com.bookstore.entity;
// Generated Aug. 3, 2022, 11:43:45 p.m. by Hibernate Tools 4.3.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Review generated by hbm2java
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Review.listAll", query = "select r from Review r order by r.reviewTime desc"),
		@NamedQuery(name = "Review.countAll", query = "select count(r) from Review r"),
		@NamedQuery(name = "Review.findByCustomerAndBook", query = "select r from Review r where r.customer.customerId = :customerId and r.book.bookId = :bookId"),
		@NamedQuery(name = "Review.mostFavoredBooks", query = "select r.book, count(r.book.bookId) as ReviewCount, avg(r.rating) as AvgRating from Review r group by r.book.bookId having avg(r.rating) >= 4.0 order by "
				+ "ReviewCount desc, AvgRating desc")
})
@Table(name = "review", catalog = "heroku_56c718d0cfb0727")
public class Review implements java.io.Serializable {

	private Integer reviewId;
	private Book book;
	private Customer customer;
	private float rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public Review() {
	}

	public Review(Book book, Customer customer, int rating, String headline, String comment, Date reviewTime) {
		this.book = book;
		this.customer = customer;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;

	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "review_id", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "book_id", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "rating", nullable = false)
	public float getRating() {
		return this.rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Column(name = "headline", nullable = false, length = 128)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time", nullable = false, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	@Transient
	public String getStars() {
		String result = "";

		int numberOfStarsOn = (int) rating;

		for (int i = 1; i <= numberOfStarsOn; i++) {
			result += "on,";
		}

		for (int j = numberOfStarsOn + 1; j <= 5; j++) {
			result += "off,";
		}

		return result.substring(0, result.length() - 1);
	}

}
