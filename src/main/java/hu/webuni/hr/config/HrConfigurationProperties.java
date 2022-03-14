package hu.webuni.hr.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigurationProperties {
	
	private Salary salary = new Salary();
	
	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public static class Salary {
		private Default def = new Default();
		private Special special = new Special();
		
		public Default getDef() {
			return def;
		}
		public void setDef(Default def) {
			this.def = def;
		}
		public Special getSpecial() {
			return special;
		}
		public void setSpecial(Special special) {
			this.special = special;
		}
	}
	
	public static class Default {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}
	
	public static class Special {
		private int lessThanTwoAnHalfYears;
		private int lessThanFiveMoreThanTwoAnHalfYears;
		private int lessThanTenMoreThanFiveYears;
		private int MoreThanTen;
		
		public int getLessThanTwoAnHalfYears() {
			return lessThanTwoAnHalfYears;
		}
		public void setLessThanTwoAnHalfYears(int lessThanTwoAnHalfYears) {
			this.lessThanTwoAnHalfYears = lessThanTwoAnHalfYears;
		}
		public int getLessThanFiveMoreThanTwoAnHalfYears() {
			return lessThanFiveMoreThanTwoAnHalfYears;
		}
		public void setLessThanFiveMoreThanTwoAnHalfYears(int lessThanFiveMoreThanTwoAnHalfYears) {
			this.lessThanFiveMoreThanTwoAnHalfYears = lessThanFiveMoreThanTwoAnHalfYears;
		}
		public int getLessThanTenMoreThanFiveYears() {
			return lessThanTenMoreThanFiveYears;
		}
		public void setLessThanTenMoreThanFiveYears(int lessThanTenMoreThanFiveYears) {
			this.lessThanTenMoreThanFiveYears = lessThanTenMoreThanFiveYears;
		}
		public int getMoreThanTen() {
			return MoreThanTen;
		}
		public void setMoreThanTen(int moreThanTen) {
			MoreThanTen = moreThanTen;
		}
	}
}
