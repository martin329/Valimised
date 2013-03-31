package com.valimised;

public class UtilitiesServlet {
	public static int getErakondId(String erakond) {
		int erakond_id = 0;
		switch (erakond) {

		case "Eesti Iseseisvuspartei":
			erakond_id = 1;
			break;
		case "Eesti Keskerakond":
			erakond_id = 2;
			break;
		case "Eesti Konservatiivne Rahvaerakond":
			erakond_id = 3;
			break;
		case "Eesti Reformierakond":
			erakond_id = 4;
			break;
		case "Eesti Vabaduspartei ñ Pıllumeeste Kogu":
			erakond_id = 5;
			break;
		case "Eestimaa ‹hendatud Vasakpartei":
			erakond_id = 6;
			break;
		case "Erakond Eesti Kristlikud Demokraadid":
			erakond_id = 7;
			break;
		case "Erakond Eestimaa Rohelised":
			erakond_id = 8;
			break;
		case "Erakond Isamaa ja Res Publica Liit":
			erakond_id = 9;
			break;
		case "Sotsiaaldemokraatlik Erakond":
			erakond_id = 10;
			break;
		case "‹ksikkandidaadid":
			erakond_id = 11;
			break;
		default:
			erakond_id = 1;
			break;
		}
		return erakond_id;
	}

	public static int getPiirkondId(String piirkond) {
		int piirkond_id = 0;
		switch (piirkond) {
		case "Tallinna Haabersti, Pıhja-Tallinna ja Kristiine linnaosa":
			piirkond_id = 1;
			break;
		case "Tallinna Kesklinna, Lasnam‰e ja Pirita linnaosa":
			piirkond_id = 2;
			break;
		case "Tallinna Mustam‰e ja Nımme linnaosa":
			piirkond_id = 3;
			break;
		case "Harju- ja Raplamaa":
			piirkond_id = 4;
			break;
		case "Hiiu-, L‰‰ne ja Saaremaa":
			piirkond_id = 5;
			break;
		case "L‰‰ne-Virumaa":
			piirkond_id = 6;
			break;
		case "Ida-Virumaa":
			piirkond_id = 7;
			break;
		case "J‰rva- ja Viljandimaa":
			piirkond_id = 8;
			break;
		case "Jıgeva- ja Tartumaa":
			piirkond_id = 9;
			break;
		case "Tartu Linn":
			piirkond_id = 10;
			break;
		case "Vıru-, Valga- ja Pılvamaa":
			piirkond_id = 11;
			break;
		case "P‰rnumaa":
			piirkond_id = 12;
			break;
		default:
			piirkond_id = 1;
			break;
		}
		return piirkond_id;

	}
}