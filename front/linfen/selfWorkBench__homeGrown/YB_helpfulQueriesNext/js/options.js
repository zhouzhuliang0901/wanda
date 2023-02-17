var areaList = [{
	"id": "01",
	"name": "基础信息"
}, {
	"id": "02",
	"name": "在管小区"
}, {
	"id": "03",
	"name": "失信信息"
},{
	"id": "04",
	"name": "业绩信息"
}]
var spaceList = {
	    "code": "200",
	    "msg": "success",
	    "data": {
	        "page": 1,
	        "totalPages": 4,
	        "pageSize": 10,
	        "totalRows": "34",
	        "content": [
	            {
	                "RN": 1,
	                "PROPERTYID": 120302184826269,
	                "PROPERTYNAME": "上海绿奕物业管理有限公司"
	            },
	            {
	                "RN": 2,
	                "PROPERTYID": 120302184852661,
	                "PROPERTYNAME": "上海绿成房产有限责任公司"
	            },
	            {
	                "RN": 3,
	                "PROPERTYID": 120302184900750,
	                "PROPERTYNAME": "上海绿洲物业管理有限公司"
	            },
	            {
	                "RN": 4,
	                "PROPERTYID": 120302184903740,
	                "PROPERTYNAME": "上海永绿置业有限公司"
	            },
	            {
	                "RN": 5,
	                "PROPERTYID": 120302184904031,
	                "PROPERTYNAME": "上海绿岛物业发展有限公司"
	            },
	            {
	                "RN": 6,
	                "PROPERTYID": 120302184905038,
	                "PROPERTYNAME": "上海绿春物业管理有限公司"
	            },
	            {
	                "RN": 7,
	                "PROPERTYID": 120302184910311,
	                "PROPERTYNAME": "上海绿安物业管理发展有限公司"
	            },
	            {
	                "RN": 8,
	                "PROPERTYID": 120302184911479,
	                "PROPERTYNAME": "上海绿岚物业管理有限公司"
	            },
	            {
	                "RN": 9,
	                "PROPERTYID": 120302184914656,
	                "PROPERTYNAME": "上海绿晖物业管理有限公司"
	            },
	            {
	                "RN": 10,
	                "PROPERTYID": 120302184915894,
	                "PROPERTYNAME": "上海绿宇物业管理有限公司"
	            }
	        ],
	        "hasPrevPage": false,
	        "hasNextPage": true
	    }
	}

var spaceInfoList = {
	code: "200", 
	msg: "success", 
	data: {
	 content: [
	  {
	   CONTACTPERSON: "陈启洪", 
	   BUSINESSTEL: "18679837182", 
	   OFFICEADDRESS: "普陀区真光路1228号衡源中环项目2号楼6F", 
	   PROPERTYQUALIFICATIONNAME: "一级资质", 
	   PROPERTYNAME: "上海永绿置业有限公司", 
	   OFFICEBUILDINGAREA: "0.00平方米", 
	   LICENSENUMBER: "", 
	   CERTIFICATE: "（建）107004", 
	   POSTCODE: "200333", 
	   BUSINESSFAX: "61155762*2019", 
	   TOTLENUMBER: 6, 
	   CERTIFICATEDATE: "2014-03-05", 
	   CSPREGISTERADDRESS: "嘉定区", 
	   CSPBIZLICENSECODE: "3101141014600", 
	   ORGCODE: "913101147354360918", 
	   JURIDICALPERSONPHONE: "18679837182", 
	   CSPBIZLICENSEVALIDDATE: "0   -  -  ", 
	   COUNTYNAME: 310114000000, 
	   BUILDINGAREA: "644,468.82平方米", 
	   RESIDENTIALBUILDINGAREA: "0.00平方米", 
	   BUSINESSTIME: "", 
	   JURIDICALPERSON: "钱杰", 
	   ISAGREE: "不约定期限"
	  }
	 ]
	}
   }


   var spaceInfoList2 = {
    "code": "200",
    "msg": "success",
    "data": {
        "content": [
            {
                "NUMBERS": "1个",
                "COUNTYID": 310110000000,
                "list": [
                    {
                        "COMMUNITYAREA": "87536",
                        "COMMUNITYID": "120302190546773",
                        "COMMUNITYNAME": "绿地汇创国际广场"
                    }
                ],
                "COUNTYNAME": "杨浦区"
            },
            {
                "NUMBERS": "4个",
                "COUNTYID": 310113000000,
                "list": [
                    {
                        "COMMUNITYAREA": "61666.85",
                        "COMMUNITYID": "1805211172906363",
                        "COMMUNITYNAME": "绿地科技大楼"
                    },
                    {
                        "COMMUNITYAREA": "222000",
                        "COMMUNITYID": "1805241173789466",
                        "COMMUNITYNAME": "绿地公园广场"
                    },
                    {
                        "COMMUNITYAREA": "123296.5",
                        "COMMUNITYID": "1805241173744324",
                        "COMMUNITYNAME": "绿地风尚天地广场"
                    },
                    {
                        "COMMUNITYAREA": "95179.47",
                        "COMMUNITYID": "1805241173738461",
                        "COMMUNITYNAME": "绿地北郊广场一期"
                    }
                ],
                "COUNTYNAME": "宝山区"
            },
            {
                "NUMBERS": "2个",
                "COUNTYID": 310114000000,
                "list": [
                    {
                        "COMMUNITYAREA": "54790",
                        "COMMUNITYID": "1805281174509106",
                        "COMMUNITYNAME": "嘉创国际商务广场"
                    },
                    {
                        "COMMUNITYAREA": "",
                        "COMMUNITYID": "2004131190203952",
                        "COMMUNITYNAME": "北虹桥绿地科技园"
                    }
                ],
                "COUNTYNAME": "嘉定区"
            }
        ]
    }
}
var spaceInfoList3 = {
    "code": "200",
    "msg": "success",
    "data": {
        "content": {
            "csp_info": [
                {
                    "CSP_ADDR": "曲沃路470弄4号",
                    "HPB_ID": 310106000000,
                    "CSP_ID": 120302184637466,
                    "CSP_STATUS": "0",
                    "CSP_CREAT_DATE": "",
                    "CSP_NAME": "上海华欣物业管理有限公司",
                    "CSP_ORG_CODE": "913101061331700546",
                    "CSP_CODE": ""
                }
            ],
            "total_score": [
                {
                    "TOTAL_SCORE": 9
                }
            ],
            "score_list": [
                {
                    "NEED_SCORING": "00",
                    "REC_DATE": "20200716",
                    "C": 1,
                    "PARA": "一",
                    "MODEL_SCORE": -0.27,
                    "CREDIT_YEAR": "",
                    "AWARD_DATE": "",
                    "ST_NAME_FRST": "九龙花苑",
                    "CREDIT_CODE": 620200724663864,
                    "END_DATE": "99991231",
                    "RECORD_DATE": "20200825",
                    "RANK": 0,
                    "CREDIT_FLAG": "",
                    "INVALID_DATE": "20210824",
                    "END_TIME": "235959",
                    "AWARD_FLAG": "",
                    "CSP_ID": 120302184637466,
                    "CSP_FLAG": "1",
                    "CSM_FLAG": "",
                    "CREATE_TIME": "145237",
                    "TE_OPERID": "913101061331700546",
                    "CREDIT_SCORE_VAL": 9,
                    "CERT_CODE": "31010119560223403X",
                    "INFO_FROM": "09",
                    "ADVISORYNUM": "",
                    "CREDIT_ID": 2008251193260124,
                    "AWARD_CONTENT": "",
                    "SECT_ID": 120302190527348,
                    "END_SCORE": -9,
                    "ITEM": "一",
                    "WINNER_NAME": "",
                    "CREATE_DATE": "20200911",
                    "WINNER_JOB": "",
                    "CREDIT_QUOTA_CONTEXT": "物业服务合同终止时，未按规定移交物业管理用房和有关资料的（沪房规范【2018】8号）",
                    "CREDIT_STATUS": "11",
                    "CSM_ID": 1609281116754955,
                    "AWARD_UNIT": "",
                    "ATTACHMENT_DATE": "20200822",
                    "LOG_ID": "",
                    "SECTION": "五",
                    "CREDIT_AWARDS": "",
                    "AWARD_YEAR": ""
                }
            ]
        }
    }
}

var spaceInfoList4 = {
    "code": "200",
    "msg": "success",
    "data": {
        "content": {
            "performanceForCsp": [],
            "satisfactionForCsp": [
                {
                    "CREDIT_SCORE_VAL": "",
                    "RANK": 637,
                    "AWARD_YEAR": "2017"
                },
                {
                    "CREDIT_SCORE_VAL": 85.98,
                    "RANK": 353,
                    "AWARD_YEAR": "2016"
                }
            ],
            "sectPerformanceForCsp": [],
            "aLevelForCsp": [
                {
                    "CREDIT_YEAR": "2017",
                    "CREDIT_AWARDS": "上海市物业管理行业诚信承诺AAA级企业"
                },
                {
                    "CREDIT_YEAR": "2014",
                    "CREDIT_AWARDS": "AA"
                },
                {
                    "CREDIT_YEAR": "2012",
                    "CREDIT_AWARDS": "AA"
                },
                {
                    "CREDIT_YEAR": "2011",
                    "CREDIT_AWARDS": "A"
                }
            ]
        }
    }
}