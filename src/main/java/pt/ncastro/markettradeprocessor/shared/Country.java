package pt.ncastro.markettradeprocessor.shared;

/**
 * This enums the possible countries with their associated codes and dialing
 * numbers. This is mainly used now to validade the input country, but it could
 * be useful for other processings in the application.
 * 
 * @author Nuno de Castro
 *
 */
public enum Country {
	AFGHANISTAN("Afghanistan", "AF", "AFG", 4, 93),
	ALBANIA("Albania", "AL", "ALB", 8, 355),
	ALGERIA("Algeria", "DZ", "DZA", 12, 213),
	ANDORRA("Andorra", "AD", "AND", 20, 376),
	ANGOLA("Angola", "AO", "AGO", 24, 244),
	ANTARCTICA("Antarctica", "AQ", "ATA", 10, 672),
	ARGENTINA("Argentina", "AR", "ARG", 32, 54),
	ARMENIA("Armenia", "AM", "ARM", 51, 374),
	ARUBA("Aruba", "AW", "ABW", 533, 297),
	AUSTRALIA("Australia", "AU", "AUS", 36, 61),
	AUSTRIA("Austria", "AT", "AUT", 40, 43),
	AZERBAIJAN("Azerbaijan", "AZ", "AZE", 31, 994),
	BAHRAIN("Bahrain", "BH", "BHR", 48, 973),
	BANGLADESH("Bangladesh", "BD", "BGD", 50, 880),
	BELARUS("Belarus", "BY", "BLR", 112, 375),
	BELGIUM("Belgium", "BE", "BEL", 56, 32),
	BELIZE("Belize", "BZ", "BLZ", 84, 501),
	BENIN("Benin", "BJ", "BEN", 204, 229),
	BHUTAN("Bhutan", "BT", "BTN", 64, 975),
	BOLIVIA("Bolivia", "BO", "BOL", 68, 591),
	BONAIRE("Bonaire", "BQ", "BES", 535, 599),
	BOSNIA("Bosnia and Herzegovina", "BA", "BIH", 70, 387),
	BOTSWANA("Botswana", "BW", "BWA", 72, 267),
	BRAZIL("Brazil", "BR", "BRA", 76, 55),
	BRUNEI("Brunei Darussalam", "BN", "BRN", 96, 673),
	BULGARIA("Bulgaria", "BG", "BGR", 100, 359),
	BURKINAFASO("Burkina Faso", "BF", "BFA", 854, 226),
	BURUNDI("Burundi", "BI", "BDI", 108, 257),
	CAMBODIA("Cambodia", "KH", "KHM", 116, 855),
	CAMEROON("Cameroon", "CM", "CMR", 120, 237),
	CANADA("Canada", "CA", "CAN", 124, 1),
	CAPEVERDE("Cape Verde", "CV", "CPV", 132, 238),
	CENTRALAFRICA("Central African Republic", "CF", "CAF", 140, 236),
	CHAD("Chad", "TD", "TCD", 148, 235),
	CHILE("Chile", "CL", "CHL", 152, 56),
	CHINA("China", "CN", "CHN", 156, 86),
	COLOMBIA("Colombia", "CO", "COL", 170, 57),
	COMOROS("Comoros", "KM", "COM", 174, 269),
	CONGO("Congo", "CG", "COG", 178, 242),
	COSTA_RICA("Costa Rica", "CR", "CRI", 188, 506),
	CROATIA("Croatia", "HR", "HRV", 191, 385),
	CUBA("Cuba", "CU", "CUB", 192, 53),
	CURACAO("Curacao", "CW", "CUW", 531, 599),
	CYPRUS("Cyprus", "CY", "CYP", 196, 357),
	CZECH_REPUBLIC("Czech Republic", "CZ", "CZE", 203, 420),
	COTE_IVOIRE("Cote d'Ivoire", "CI", "CIV", 384, 225),
	DENMARK("Denmark", "DK", "DNK", 208, 45),
	DJIBOUTI("Djibouti", "DJ", "DJI", 262, 253),
	ECUADOR("Ecuador", "EC", "ECU", 218, 593),
	EGYPT("Egypt", "EG", "EGY", 818, 20),
	EL_SALVADOR("El Salvador", "SV", "SLV", 222, 503),
	EQUATORIAL_GUINEA("Equatorial Guinea", "GQ", "GNQ", 226, 240),
	ERITREA("Eritrea", "ER", "ERI", 232, 291),
	ESTONIA("Estonia", "EE", "EST", 233, 372),
	ETHIOPIA("Ethiopia", "ET", "ETH", 231, 251),
	MALVINAS("Falkland Islands (Malvinas)", "FK", "FLK", 238, 500),
	FAROE_ISLANDS("Faroe Islands", "FO", "FRO", 234, 298),
	FIJI("Fiji", "FJ", "FJI", 242, 679),
	FINLAND("Finland", "FI", "FIN", 246, 358),
	FRANCE("France", "FR", "FRA", 250, 33),
	GABON("Gabon", "GA", "GAB", 266, 241),
	GAMBIA("Gambia", "GM", "GMB", 270, 220),
	GEORGIA("Georgia", "GE", "GEO", 268, 995),
	GERMANY("Germany", "DE", "DEU", 276, 49),
	GHANA("Ghana", "GH", "GHA", 288, 233),
	GIBRALTAR("Gibraltar", "GI", "GIB", 292, 350),
	GREECE("Greece", "GR", "GRC", 300, 30),
	GREENLAND("Greenland", "GL", "GRL", 304, 299),
	Guadeloupe("Guadeloupe", "GP", "GLP", 312, 590),
	GUATEMALA("Guatemala", "GT", "GTM", 320, 502),
	GUERNSEY("Guernsey", "GG", "GGY", 831, 44),
	GUINEA("Guinea", "GN", "GIN", 324, 224),
	GUINEA_BISSAU("Guinea-Bissau", "GW", "GNB", 624, 245),
	GUYANA("Guyana", "GY", "GUY", 328, 592),
	HAITI("Haiti", "HT", "HTI", 332, 509),
	VATICAN("Holy See (Vatican City State)", "VA", "VAT", 336, 379),
	HONDURAS("Honduras", "HN", "HND", 340, 504),
	HONG_KONG("Hong Kong", "HK", "HKG", 344, 852),
	HUNGARY("Hungary", "HU", "HUN", 348, 36),
	ICELAND("Iceland", "IS", "ISL", 352, 354),
	INDIA("India", "IN", "IND", 356, 91),
	INDONESIA("Indonesia", "ID", "IDN", 360, 62),
	IRAN("Islamic Republic of Iran", "IR", "IRN", 364, 98),
	IRAQ("Iraq", "IQ", "IRQ", 368, 964),
	IRELAND("Ireland", "IE", "IRL", 372, 353),
	ISLE_OF_MAN("Isle of Man", "IM", "IMN", 833, 44),
	ISRAEL("Israel", "IL", "ISR", 376, 972),
	ITALY("Italy", "IT", "ITA", 380, 39),
	JAPAN("Japan", "JP", "JPN", 392, 81),
	JERSEY("Jersey", "JE", "JEY", 832, 44),
	JORDAN("Jordan", "JO", "JOR", 400, 962),
	KAZAKHSTAN("Kazakhstan", "KZ", "KAZ", 398, 7),
	KENYA("Kenya", "KE", "KEN", 404, 254),
	KIRIBATI("Kiribati", "KI", "KIR", 296, 686),
	NORTH_KOREA("Democratic People's Republic of Korea", "KP", "PRK", 408, 850),
	SOUTH_KOREA("Republic of Korea", "KR", "KOR", 410, 82),
	KUWAIT("Kuwait", "KW", "KWT", 414, 965),
	KYRGYZSTAN("Kyrgyzstan", "KG", "KGZ", 417, 996),
	LATVIA("Latvia", "LV", "LVA", 428, 371),
	LEBANON("Lebanon", "LB", "LBN", 422, 961),
	LESOTHO("Lesotho", "LS", "LSO", 426, 266),
	LIBERIA("Liberia", "LR", "LBR", 430, 231),
	LIBYA("Libya", "LY", "LBY", 434, 218),
	LIECHTENSTEIN("Liechtenstein", "LI", "LIE", 438, 423),
	LITHUANIA("Lithuania", "LT", "LTU", 440, 370),
	LUXEMBOURG("Luxembourg", "LU", "LUX", 442, 352),
	MACAO("Macao", "MO", "MAC", 446, 853),
	MACEDONIA("Macedonia", "MK", "MKD", 807, 389),
	MADAGASCAR("Madagascar", "MG", "MDG", 450, 261),
	MALAWI("Malawi", "MW", "MWI", 454, 265),
	MALAYSIA("Malaysia", "MY", "MYS", 458, 60),
	MALDIVES("Maldives", "MV", "MDV", 462, 960),
	MALI("Mali", "ML", "MLI", 466, 223),
	MALTA("Malta", "MT", "MLT", 470, 356),
	MARTINIQUE("Martinique", "MQ", "MTQ", 474, 596),
	MAURITANIA("Mauritania", "MR", "MRT", 478, 222),
	MAURITIUS("Mauritius", "MU", "MUS", 480, 230),
	MAYOTTE("Mayotte", "YT", "MYT", 175, 262),
	MEXICO("Mexico", "MX", "MEX", 484, 52),
	MOLDOVA("Republic of Moldova", "MD", "MDA", 498, 373),
	MONACO("Monaco", "MC", "MCO", 492, 377),
	MONGOLIA("Mongolia", "MN", "MNG", 496, 976),
	MONTENEGRO("Montenegro", "ME", "MNE", 499, 382),
	MOROCCO("Morocco", "MA", "MAR", 504, 212),
	MOZAMBIQUE("Mozambique", "MZ", "MOZ", 508, 258),
	MYANMAR("Myanmar", "MM", "MMR", 104, 95),
	NAMIBIA("Namibia", "NA", "NAM", 516, 264),
	NAURU("Nauru", "NR", "NRU", 520, 674),
	NEPAL("Nepal", "NP", "NPL", 524, 977),
	NETHERLANDS("Netherlands", "NL", "NLD", 528, 31),
	NEW_ZEALAND("New Zealand", "NZ", "NZL", 554, 64),
	NICARAGUA("Nicaragua", "NI", "NIC", 558, 505),
	NIGER("Niger", "NE", "NER", 562, 227),
	NIGERIA("Nigeria", "NG", "NGA", 566, 234),
	NIUE("Niue", "NU", "NIU", 570, 683),
	NORWAY("Norway", "NO", "NOR", 578, 47),
	OMAN("Oman", "OM", "OMN", 512, 968),
	PAKISTAN("Pakistan", "PK", "PAK", 586, 92),
	PALAU("Palau", "PW", "PLW", 585, 680),
	PALESTINE("State of Palestine", "PS", "PSE", 275, 970),
	PANAMA("Panama", "PA", "PAN", 591, 507),
	PAPUA_NEW_GUINEA("Papua New Guinea", "PG", "PNG", 598, 675),
	PARAGUAY("Paraguay", "PY", "PRY", 600, 595),
	PERU("Peru", "PE", "PER", 604, 51),
	PHILIPPINES("Philippines", "PH", "PHL", 608, 63),
	PITCAIRN("Pitcairn", "PN", "PCN", 612, 870),
	POLAND("Poland", "PL", "POL", 616, 48),
	PORTUGAL("Portugal", "PT", "PRT", 620, 351),
	PUERTO_RICO("Puerto Rico", "PR", "PRI", 630, 1),
	QATAR("Qatar", "QA", "QAT", 634, 974),
	ROMANIA("Romania", "RO", "ROU", 642, 40),
	RUSSIA("Russian Federation", "RU", "RUS", 643, 7),
	RWANDA("Rwanda", "RW", "RWA", 646, 250),
	REUNION("Reunion", "RE", "REU", 638, 262),
	SAMOA("Samoa", "WS", "WSM", 882, 685),
	SAN_MARINO("San Marino", "SM", "SMR", 674, 378),
	SAO_TOME_PRINCIPE("Sao Tome and Principe", "ST", "STP", 678, 239),
	SAUDI_ARABIA("Saudi Arabia", "SA", "SAU", 682, 966),
	SENEGAL("Senegal", "SN", "SEN", 686, 221),
	SERBIA("Serbia", "RS", "SRB", 688, 381),
	SEYCHELLES("Seychelles", "SC", "SYC", 690, 248),
	SIERRA_LEONE("Sierra Leone", "SL", "SLE", 694, 232),
	SINGAPORE("Singapore", "SG", "SGP", 702, 65),
	SLOVAKIA("Slovakia", "SK", "SVK", 703, 421),
	SLOVENIA("Slovenia", "SI", "SVN", 705, 386),
	SOLOMON_ISLANDS("Solomon Islands", "SB", "SLB", 90, 677),
	SOMALIA("Somalia", "SO", "SOM", 706, 252),
	SOUTH_AFRICA("South Africa", "ZA", "ZAF", 710, 27),
	SPAIN("Spain", "ES", "ESP", 724, 34),
	SRI_LANKA("Sri Lanka", "LK", "LKA", 144, 94),
	SUDAN("Sudan", "SD", "SDN", 729, 249),
	SURINAME("Suriname", "SR", "SUR", 740, 597),
	SWAZILAND("Swaziland", "SZ", "SWZ", 748, 268),
	SWEDEN("Sweden", "SE", "SWE", 752, 46),
	SWITZERLAND("Switzerland", "CH", "CHE", 756, 41),
	SYRIA("Syrian Arab Republic", "SY", "SYR", 760, 963),
	TAIWAN("Taiwan", "TW", "TWN", 158, 886),
	TAJIKISTAN("Tajikistan", "TJ", "TJK", 762, 992),
	TANZANIA("United Republic of Tanzania", "TZ", "TZA", 834, 255),
	THAILAND("Thailand", "TH", "THA", 764, 66),
	TIMOR_LESTE("Timor-Leste", "TL", "TLS", 626, 670),
	TOGO("Togo", "TG", "TGO", 768, 228),
	TOKELAU("Tokelau", "TK", "TKL", 772, 690),
	TONGA("Tonga", "TO", "TON", 776, 676),
	TUNISIA("Tunisia", "TN", "TUN", 788, 216),
	TURKEY("Turkey", "TR", "TUR", 792, 90),
	TURKMENISTAN("Turkmenistan", "TM", "TKM", 795, 993),
	TUVALU("Tuvalu", "TV", "TUV", 798, 688),
	UGANDA("Uganda", "UG", "UGA", 800, 256),
	UKRAINE("Ukraine", "UA", "UKR", 804, 380),
	ARAB_EMIRATES("United Arab Emirates", "AE", "ARE", 784, 971),
	UNITED_KINGDOM("United Kingdom", "GB", "GBR", 826, 44),
	UNITED_STATES("United States", "US", "USA", 840, 1),
	URUGUAY("Uruguay", "UY", "URY", 858, 598),
	UZBEKISTAN("Uzbekistan", "UZ", "UZB", 860, 998),
	VANUATU("Vanuatu", "VU", "VUT", 548, 678),
	VENEZUELA("Venezuela", "VE", "VEN", 862, 58),
	VIETNAM("Vietnam", "VN", "VNM", 704, 84),
	YEMEN("Yemen", "YE", "YEM", 887, 967),
	ZAMBIA("Zambia", "ZM", "ZMB", 894, 260),
	ZIMBABWE("Zimbabwe", "ZW", "ZWE", 716, 263),

	;

	private final String name;
	private final String isoCode;
	private final String unCode;
	private final int unNum;
	private final int dialingNum;


	/**
	 * Internal constructor.
	 * 
	 * @param name
	 * @param isoCode 2-char code following the ISO format
	 * @param unCode 3-char code as identified in the United Nations
	 * @param unNum Country number in the United Nations
	 * @param dialingNum
	 */
	private Country(String name, String isoCode, String unCode, int unNum, int dialingNum) {
		this.name = name;
		this.isoCode = isoCode;
		this.unCode = unCode;
		this.unNum = unNum;
		this.dialingNum = dialingNum;
	}


	/**
	 * Returns the country name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Returns the country code in ISO format.
	 * 
	 * @return the isoCode
	 */
	public String getISOCode() {
		return isoCode;
	}


	/**
	 * Return the country code in United Nations format.
	 * 
	 * @return the unCode
	 */
	public String getUNCode() {
		return unCode;
	}


	/**
	 * Return the country number from United Nations numbering.
	 * 
	 * @return the unNum
	 */
	public int getUNNum() {
		return unNum;
	}


	/**
	 * Return the country dialing number.
	 * 
	 * @return the dialingNum
	 */
	public int getDialingNum() {
		return dialingNum;
	}



	/**
	 * Returns a country from its ISO code.
	 * 
	 * @param isoCode
	 * @return
	 */
	public static Country fromISOCode(String isoCode) {
		if (isoCode != null && isoCode.length() == 2) {
			for (Country c : Country.values()) {
				if (c.isoCode.equals(isoCode)) {
					return c;
				}
			}
		}
		return null;
	}
}
