package world.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String code;
    private String name;
    private String continent;
    private String region;
    private int population;

  

    public Country(String code, String name, int population) {
        this.code = code;
        this.name = name;
        this.population = population;
    }
    
    @OneToOne
    @JoinColumn(name = "capital")
    private City capital;

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<City> myCities;

    public String getCode() {
        return code;
    }

    public Set<City> getMyCities() {
        return myCities;
    }

    public void setMyCities(Set<City> myCities) {
        this.myCities = myCities;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", continent="
                + continent + ", region=" + region + ", population=" + population
                + ", capital=" + capital + '}';
    }

}
