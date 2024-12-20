package nl.suzannawentzel.wicketcompact.entities;
public class Table extends BaseEntity
{
	private String name;
	private Integer seatCount;

	private Boolean orderableElectronically = Boolean.TRUE;

	public Table(String name, Integer seatCount) {
		this.name = name;
		this.seatCount = seatCount;
		this.orderableElectronically = Boolean.FALSE;
	}

	public Table() {
		this(null, null);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(Integer seatCount) {
		this.seatCount = seatCount;
	}
	public Boolean getOrderableElectronically() {
		return orderableElectronically;
	}
	public void setOrderableElectronically(Boolean orderableElectronically) {
		this.orderableElectronically = orderableElectronically;
	}
}
