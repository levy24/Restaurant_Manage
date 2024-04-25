package admin;

import data_cache.Drink_Cache;
import data_cache.Food_Cache;

public class Chuc_Nang {

	public Chuc_Nang() {
		// TODO Auto-generated constructor stub
	}
	public static void Tim_kiem(String x) {
		int i = Food_Cache.FName.indexOf(x);
		Object[] rowData = new Object[4];
		if(i != -1) {
			
			rowData[0] = Food_Cache.FID.get(i);
            rowData[1] = Food_Cache.FName.get(i);
            rowData[2] = Food_Cache.FPrice.get(i);
            rowData[3] = Food_Cache.FQuantity.get(i);
		}
		
		CTC.Set_tabelModel(rowData);
	}
	public static void Tim_kiem2(String x) {
		
		int i = Drink_Cache.Drink_Name.indexOf(x);
		Object[] rowData = new Object[4];
		if(i != -1) {
            rowData[0] = Drink_Cache.Drink_ID.get(i);
            rowData[1] = Drink_Cache.Drink_Name.get(i);
            rowData[2] = Drink_Cache.Drink_Price.get(i);
            rowData[3] = Drink_Cache.Drink_Quantity.get(i);
		}
		
		CTC.Set_tabelModel(rowData);
	}
}
