/**
 *  구글 차트 API 스크립트
 */
//지난달 이번달 매출액 비교 막대 차트
google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart);
				
function drawChart() {

	var TMBill = parseInt(document.getElementById("TMBill").value);
	var LMBill = parseInt(document.getElementById("LMBill").value);

	var data = google.visualization.arrayToDataTable([
        ['Month', '매출액(원)'],
        ['지난달', LMBill],
        ['이번달', TMBill]
    ]);
	
	  
	var options = {
	    chart: {
	      title: '전월 대비 매출',
	      subtitle: '지난달과 이번달 매출 비교입니다.',
	    },
	    bars: 'horizontal' // Required for Material Bar Charts.
	};
	
	var chart = new google.charts.Bar(document.getElementById('barchart_values'));
	
	chart.draw(data, google.charts.Bar.convertOptions(options));
}

google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawChart2);
	
function drawChart2() {
  
	var LMBill_10 = parseInt(document.getElementById("LMBill_10").value);
	var TMBill_10 = parseInt(document.getElementById("TMBill_10").value);
  	var LMBill_20 = parseInt(document.getElementById("LMBill_20").value);
	var TMBill_20 = parseInt(document.getElementById("TMBill_20").value);
  	var LMBill_30 = parseInt(document.getElementById("LMBill_30").value);
	var TMBill_30 = parseInt(document.getElementById("TMBill_30").value);
	var LMBill_40 = parseInt(document.getElementById("LMBill_40").value);
	var TMBill_40 = parseInt(document.getElementById("TMBill_40").value);
	
    var data = google.visualization.arrayToDataTable([
	    ['연령대', '지난달', '이번달'],
	    ['10대', LMBill_10, TMBill_10],
	    ['20대', LMBill_20, TMBill_20],
	    ['30대', LMBill_30, TMBill_30],
	    ['40대', LMBill_40, TMBill_40]
    ]);

    var options = {
	    chart: {
		    title: '연령별 매출 추이',
		    subtitle: '지난달과 이번달 연령별 매출 비교입니다.',
	    },
	    bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.charts.Bar(document.getElementById('barchart_values2'));

    chart.draw(data, google.charts.Bar.convertOptions(options));
}

