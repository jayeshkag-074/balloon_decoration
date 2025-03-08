package com.amstech.balloon.decoration.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the decorations database table.
 * 
 */
@Entity
@Table(name="decorations")
@NamedQuery(name="Decoration.findAll", query="SELECT d FROM Decoration d")
public class Decoration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_at")
	private Date createAt;

	@Lob
	private String description;

	@Column(name="image_url")
	private String imageUrl;

	private String name;

	private double price;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="decoration")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Colour
	@OneToMany(mappedBy="decoration")
	private List<Colour> colours;

	//bi-directional many-to-one association to DecorationBanner
	@OneToMany(mappedBy="decoration")
	private List<DecorationBanner> decorationBanners;

	//bi-directional many-to-one association to Decorater
	@ManyToOne
	private Decorater decorater;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	//bi-directional many-to-one association to Status
	@ManyToOne
	private Status status;

	//bi-directional many-to-one association to Design
	@OneToMany(mappedBy="decoration")
	private List<Design> designs;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="decoration")
	private List<Event> events;

	//bi-directional many-to-one association to Feedback
	@OneToMany(mappedBy="decoration")
	private List<Feedback> feedbacks;

	//bi-directional many-to-one association to Material
	@OneToMany(mappedBy="decoration")
	private List<Material> materials;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="decoration")
	private List<Notification> notifications;

	//bi-directional many-to-one association to Size
	@OneToMany(mappedBy="decoration")
	private List<Size> sizes;

	//bi-directional many-to-one association to Type
	@OneToMany(mappedBy="decoration")
	private List<Type> types;

	public Decoration() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return this.createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setDecoration(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setDecoration(null);

		return booking;
	}

	public List<Colour> getColours() {
		return this.colours;
	}

	public void setColours(List<Colour> colours) {
		this.colours = colours;
	}

	public Colour addColour(Colour colour) {
		getColours().add(colour);
		colour.setDecoration(this);

		return colour;
	}

	public Colour removeColour(Colour colour) {
		getColours().remove(colour);
		colour.setDecoration(null);

		return colour;
	}

	public List<DecorationBanner> getDecorationBanners() {
		return this.decorationBanners;
	}

	public void setDecorationBanners(List<DecorationBanner> decorationBanners) {
		this.decorationBanners = decorationBanners;
	}

	public DecorationBanner addDecorationBanner(DecorationBanner decorationBanner) {
		getDecorationBanners().add(decorationBanner);
		decorationBanner.setDecoration(this);

		return decorationBanner;
	}

	public DecorationBanner removeDecorationBanner(DecorationBanner decorationBanner) {
		getDecorationBanners().remove(decorationBanner);
		decorationBanner.setDecoration(null);

		return decorationBanner;
	}

	public Decorater getDecorater() {
		return this.decorater;
	}

	public void setDecorater(Decorater decorater) {
		this.decorater = decorater;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Design> getDesigns() {
		return this.designs;
	}

	public void setDesigns(List<Design> designs) {
		this.designs = designs;
	}

	public Design addDesign(Design design) {
		getDesigns().add(design);
		design.setDecoration(this);

		return design;
	}

	public Design removeDesign(Design design) {
		getDesigns().remove(design);
		design.setDecoration(null);

		return design;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setDecoration(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setDecoration(null);

		return event;
	}

	public List<Feedback> getFeedbacks() {
		return this.feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Feedback addFeedback(Feedback feedback) {
		getFeedbacks().add(feedback);
		feedback.setDecoration(this);

		return feedback;
	}

	public Feedback removeFeedback(Feedback feedback) {
		getFeedbacks().remove(feedback);
		feedback.setDecoration(null);

		return feedback;
	}

	public List<Material> getMaterials() {
		return this.materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public Material addMaterial(Material material) {
		getMaterials().add(material);
		material.setDecoration(this);

		return material;
	}

	public Material removeMaterial(Material material) {
		getMaterials().remove(material);
		material.setDecoration(null);

		return material;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setDecoration(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setDecoration(null);

		return notification;
	}

	public List<Size> getSizes() {
		return this.sizes;
	}

	public void setSizes(List<Size> sizes) {
		this.sizes = sizes;
	}

	public Size addSize(Size size) {
		getSizes().add(size);
		size.setDecoration(this);

		return size;
	}

	public Size removeSize(Size size) {
		getSizes().remove(size);
		size.setDecoration(null);

		return size;
	}

	public List<Type> getTypes() {
		return this.types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public Type addType(Type type) {
		getTypes().add(type);
		type.setDecoration(this);

		return type;
	}

	public Type removeType(Type type) {
		getTypes().remove(type);
		type.setDecoration(null);

		return type;
	}

}