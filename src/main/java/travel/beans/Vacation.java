package travel.beans;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="vacation")
public class Vacation {
	@Id
	@GeneratedValue
	
	@Column(name="ID")
	private int id;
	
	@Size(min = 1, max = 50, message = "name must be between 1 and 50 characters.")
	@Column(name="NAME")
	private String name;
	
	@Size(min = 2, max = 50)
	@NotBlank(message = "Please enter a destination.")
	@Column(name="DESTINATION")
	private String destination;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull(message = "Please enter a departure date.")
	@Column(name="STARTDATE")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd") 
	@NotNull(message = "Please enter a return date.")
	@Column(name="ENDDATE")
	private LocalDate endDate;
	
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@Column(name="ACTIVITIES")
	private List<Activity> activities;
	
	@Autowired
	private Traveler traveler;

	/**
	 * Constructor that takes id, name, destination, startDate, endDate, activities, and travellers as parameters
	 * @param id
	 * @param name
	 * @param destination
	 * @param startDate
	 * @param endDate
	 * @param activities
	 * @param travellers
	 */
	public Vacation(int id, String name, String destination, LocalDate startDate, LocalDate endDate,
			List<Activity> activities) {
		setId(id);
		setDestination(destination);
		setStartDate(startDate);
		setEndDate(endDate);
		setActivities(activities);
		setName(name);
	}
	
	/**
	 * Constructor that takes name, destination, startDate, endDate, activities, and travellers as parameters
	 * @param name
	 * @param destination
	 * @param startDate
	 * @param endDate
	 * @param activities
	 * @param travellers
	 */
	public Vacation(String name, String destination, LocalDate startDate, LocalDate endDate,
			List<Activity> activities) {
		setDestination(destination);
		setStartDate(startDate);
		setEndDate(endDate);
		setActivities(activities);
		setName(name);
	}
	
	
	/**
	 * Constructor that takes name, destination, startDate, and endDate as parameters
	 * @param name
	 * @param destination
	 * @param startDate
	 * @param endDate
	 */
	public Vacation(String name, String destination, LocalDate startDate, LocalDate endDate) {
		setDestination(destination);
		setStartDate(startDate);
		setEndDate(endDate);
		setActivities(activities);
		setName(name);
	}
}