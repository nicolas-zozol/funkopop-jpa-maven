package io.robusta.fora.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MetaData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id = 1L;
	boolean dataFilled = false;
	boolean fillMany = false;
	boolean askDelete = false;
	
	
	public MetaData() {
		
	}


	public boolean isDataFilled() {
		return dataFilled;
	}


	public void setDataFilled(boolean dataFilled) {
		this.dataFilled = dataFilled;
	}


	public boolean isAskDelete() {
		return askDelete;
	}


	public void setAskDelete(boolean askDelete) {
		this.askDelete = askDelete;
	}
	
	
	
	
	
}
