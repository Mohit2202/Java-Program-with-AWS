package com.amazonaws.lambda.demo;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.c2m.email.EmailBean;
import com.c2m.email.JavaMailEmailService;

public class EmailServiceforCampaign implements RequestHandler<EmailRequest, String> {

	@Override
	public String handleRequest(EmailRequest request, Context context) {

		String SendToConsole = String.format(
				"Your Created Campaign: JobTemplate = %s, Event = %s, County = %s, Email = %s.", request.jobTemplate,
				request.event, request.county, request.email);
		return sendEmail(request);
	}

	public static String methodE(EmailRequest request){


		String EmailFormat = "<html xmlns='http://www.w3.org/1999/xhtml'>";

		EmailFormat += "<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /><title>Welcome</title><meta name='viewport' content='width=device-width, initial-scale=1.0'/></head>";

		 EmailFormat += "<body bgcolor='#ECECEC'><table border='0' cellpadding='60' cellspacing='0' style='background-color: #ECECEC' background='https://ci3.googleusercontent.com/proxy/pvjwGaT3qvZe9xvWcef31KNbY7tREozGpgOhBW3WlicNNeeT1RoTslT7EbyhoYaD3Zpz4m0_SEr7OZDpAQBzreXHDCvt6SXXJ8QB6GIvDjBAa0EWINmz4Rk2cH4E=s0-d-e1-ft#http://email.images.apple.com/dm/groups/aos/om/global/cmon/bg-noise.gif' align='center' width='100%'><tr><td>";

	//TODO
	
	EmailFormat +=  "<table background='https://www.google.com/intl/en_com/images/srpr/logo3w.png' width='700' height='80' align='center' border='0' cellpadding='0' cellspacing='0' class='content' style='background-color: #FFFFFF'><tr><td id='templateContainerHeader' valign='top' mc:edit='welcomeEdit-01'>";

	EmailFormat +=  "<p style='text-align:center;margin:0;padding:0'>";

	//TODO Apple IMG
	EmailFormat += "<img src='https://ci3.googleusercontent.com/proxy/Tsa80rg9I-hdOJhhulGjMyyQhj09MwqEo1LydWM5cfMPzQCQ9jumWw50ruCu6Wu74qeHVcdUsfX3CkNyIh4CzErww_c6SHg7iYcjZt3U1KpuP-WIYACkgnwgXCJx-A=s0-d-e1-ft#http://email.images.apple.com/dm/groups/aos/om/global/cmon/hr-shaded.gif' style='outline:none;display:block' class='m_3502137361431265173m_-3675575072246215882ydp13f1fbeayiv7539114798mobile-scale CToWUd' width='700' height='7' border='0'>";

	EmailFormat += "<img src='http://photos.prnewswire.com/prnvar/20110930/PH78108LOGO' style='display:inline-block;'</img></p>";

	EmailFormat += "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' class='brdBottomPadd-two' id='templateContainer' width='100%'><tr>";

    EmailFormat += "<td class='bodyContent' valign='top' mc:edit='welcomeEdit-02'>";

    //TODO
    EmailFormat += "<img src='https://ci3.googleusercontent.com/proxy/Tsa80rg9I-hdOJhhulGjMyyQhj09MwqEo1LydWM5cfMPzQCQ9jumWw50ruCu6Wu74qeHVcdUsfX3CkNyIh4CzErww_c6SHg7iYcjZt3U1KpuP-WIYACkgnwgXCJx-A=s0-d-e1-ft#http://email.images.apple.com/dm/groups/aos/om/global/cmon/hr-shaded.gif' style='outline:none;display:block' class='m_3502137361431265173m_-3675575072246215882ydp13f1fbeayiv7539114798mobile-scale CToWUd' width='700' height='7' border='0'>";


    EmailFormat +=  "<p>Hello!</p>";

    EmailFormat += "<h1><strong>Congratulations on signing up<br>for Click2NotifyWeather</strong></h1>";

    EmailFormat +=   "<h3>Thanks for creating a campaign. We are contacting you to ensure that your expectations were met for your recent Click2Mail.com order.</h3></td></tr>";

    EmailFormat += "<tr align='top'><td class='bodyContentImage' valign='top'><table border='0' cellpadding='0' cellspacing='0'><tr><td align='left' style='margin:0;padding:0;' valign='top' width='50' mc:edit='welcomeEdit-03'><p style='margin-bottom:10px'><img src='http://c0185784a2b233b0db9b-d0e5e4adc266f8aacd2ff78abb166d77.r51.cf2.rackcdn.com/templates/img_profile.jpgs' style='display:block;'></p></td>";

    EmailFormat += "<td align='left' style='width:15px;margin:0;padding:0;' valign='top' width='15'>&nbsp;</td><td align='left' style='margin:0;padding-top:10px;line-height:1;' valign='top' mc:edit='welcomeEdit-04'>";

    EmailFormat += "<h4><strong></strong></h4><h5></h5></td></tr></table></td></tr></table></td></tr>";

    EmailFormat += "<tr><td align='center'>";

    EmailFormat += "<table border='0' cellpadding='0' cellspacing='0' class='brdBottomPadd-two ' id='templateContainerMiddle' width='100%'><tr valign='top'><td align='center' class='bodyContentTicks'><table border='0' cellpadding='0' cellspacing='0' width='100%'>";

    EmailFormat += "<tr valign='top'><td valign='top' mc:edit='welcomeEditImgFirst' style='width:19%;color:#505050;font-family:Helvetica;font-size:14px;padding-bottom:1.143em;'><p style='text-align:center;margin:0 0 15px 0;padding:0;'><img height='' src='http://c0185784a2b233b0db9b-d0e5e4adc266f8aacd2ff78abb166d77.r51.cf2.rackcdn.com/templates/circle.jpg' style='display:block;' width='91'></p></td>";

    EmailFormat += "<td valign='top' style='width:5%;'>&nbsp;</td><td valign='top' mc:edit='welcomeEditTxtFirst' style='width:71%;color:#505050;font-family:Helvetica;font-size:14px;padding-top:0.714em;'><h3><strong>Login with Click2NotifyWeather</strong></h3><h4>Register with Click2Mail website before logging into Click2NotifyWeather</h4></td><td valign='top' style='width:5%;'>&nbsp;</td></tr>";

    EmailFormat += "<tr valign='top'><td valign='top' mc:edit='welcomeEditImgFirst' style='width:19%;color:#505050;font-family:Helvetica;font-size:14px;padding-bottom:1.143em;'><p style='text-align:center;margin:0 0 15px 0;padding:0;'><img height='' src='http://c0185784a2b233b0db9b-d0e5e4adc266f8aacd2ff78abb166d77.r51.cf2.rackcdn.com/templates/circle.jpg' style='display:block;' width='91'></p></td>";

    EmailFormat += "<td valign='top' style='width:5%;'>&nbsp;</td><td valign='top' mc:edit='welcomeEditTxtFirst' style='width:71%;color:#505050;font-family:Helvetica;font-size:14px;padding-top:0.714em;'><h3><strong>Create your Campaign</strong></h3><h4>You have created a campaign in county : " + request.getCounty() + "<br>";

    EmailFormat += " Your Event : " + request.getEvent()  + "</h4></td><td valign='top' style='width:5%;'>&nbsp;</td></tr>";

    EmailFormat += "<tr valign='top'><td valign='top' mc:edit='welcomeEditImgFirst' style='width:19%;color:#505050;font-family:Helvetica;font-size:14px;padding-bottom:1.143em;'><p style='text-align:center;margin:0 0 15px 0;padding:0;'><img height='' src='http://c0185784a2b233b0db9b-d0e5e4adc266f8aacd2ff78abb166d77.r51.cf2.rackcdn.com/templates/circle.jpg' style='display:block;' width='91'></p></td>";

    EmailFormat += "<td valign='top' style='width:5%;'>&nbsp;</td><td valign='top' mc:edit='welcomeEditTxtFirst'><h3><strong>Subscribe for Creating another campaign</strong></h3><h4>Our community is as good as its members. You can subscribe for another Campaign.</h4></td><td valign='top' style='width:5%;'>&nbsp;</td></tr></table></td></tr></table></td></tr>";

    EmailFormat += "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' id='templateContainerMiddleBtm' width='100%'><tr><td class='bodyContent' valign='top' mc:edit='welcomeEdit-11'><h3><strong>Get a 10% discount on your next Campaign</strong></h3><h4>Exclusive to existing members, receive a 20%discount on your Campaign when you use the coupon code MYCAMPAIGNAGAIN. Click on the button below to confirm and send the Coupons!</h4><a href='http://awsversion8tomcatserver.sc5ndzrnax.us-east-1.elasticbeanstalk.com/FinalSubmit.html'><strong><button type='button'>OneClickSend</button><br></strong></a></td></tr></table></td></tr>";

     //EmailFormat += "<tr><td align='center' class='unSubContent' id='bodyCellFooter' valign='top'><table border='0' cellpadding='0' cellspacing='0' id='templateContainerFooter' width='100%'><tr><td valign='top' width='100%' mc:edit='welcomeEdit-11'><p style='text-align:center;'><img src='http://c0185784a2b233b0db9b-d0e5e4adc266f8aacd2ff78abb166d77.r51.cf2.rackcdn.com/templates/cog-03.jpg' style='margin:0 auto 0 auto;display:inline-block;'></p><h6 style='text-align:center;margin-top: 9px;'>COG Inc</h6><h6 style='text-align:center;'>589&#8203; Howard&#8203; Street&#8203;</h6><h6 style='text-align:center;'>San&#8203; Francisco,&#8203; CA&#8203; 94105&#8203;</h6><h6 style='text-align:center;margin-top: 7px;'><a href='--unsubscribe--'>unsubscribe</a></h6></td></tr></table></td></tr></table></td></tr></table></td></tr></table>";
    EmailFormat += "<tr><td align='center' class='unSubContent' id='bodyCellFooter' valign='top'><table border='0' cellpadding='0' cellspacing='0' id='templateContainerFooter' width='100%'><tr><td valign='top' width='100%' mc:edit='welcomeEdit-11'><p style='text-align:center;'></p><h6 style='text-align:center;margin-top: 9px;'>C2M LLC</h6><h6 style='text-align:center;'>3103&#8203; 10th&#8203; Street N, Suite 201&#8203;</h6><h6 style='text-align:center;'>Arlington&#8203; VA&#8203; 22201&#8203;</h6><h6 style='text-align:center;margin-top: 7px;'><a href='--unsubscribe--'>unsubscribe</a></h6></td></tr></table></td></tr></table></td></tr></table></td></tr></table>";

    EmailFormat += "<style type='text/css'>span.preheader {display:none!important}td ul li {font-size: 16px;}";

    EmailFormat += "#outlook a {padding:0}.ReadMsgBody {width:100%}.ExternalClass {width:100%}.ExternalClass,.ExternalClass p,.ExternalClass span,.ExternalClass font,.ExternalClass td,.ExternalClass div {line-height:100%}";

    EmailFormat +=" body,table,td,p,a,li,blockquote {-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%}table,td {mso-table-lspace:0;mso-table-rspace:0}";

    EmailFormat += " body {margin:0;padding:0}img {max-width:100%;border:0;line-height:100%;outline:none;text-decoration:none}table {border-collapse:collapse!important}";

    EmailFormat += " .content {width:100;max-width:600px}.content img {height:auto;min-height:1px}#bodyTable {margin:0;padding:0;width:100%!important}";

    EmailFormat += " #bodyCell {margin:0;padding:0}#bodyCellFooter {margin:0;padding:0;width:100%!important;padding-top:39px;padding-bottom:15px}";

    EmailFormat += "body {margin:0;padding:0;min-width:100%!important}#templateContainerHeader {font-size:14px;padding-top:2.429em;padding-bottom:.929em}";

    EmailFormat += "#templateContainerFootBrd {border-bottom:1px solid #e2e2e2;border-left:1px solid #e2e2e2;border-right:1px solid #e2e2e2;border-radius:0 0 4px 4px;background-clip:padding-box;border-spacing:0;height:10px;width:100%!important}";

    EmailFormat += "#templateContainer {border-top:1px solid #e2e2e2;border-left:1px solid #e2e2e2;border-right:1px solid #e2e2e2;border-radius:4px 4px 0 0;background-clip:padding-box;border-spacing:0}";

    EmailFormat += "#templateContainerMiddle {border-left:1px solid #e2e2e2;border-right:1px solid #e2e2e2}";

    EmailFormat += "#templateContainerMiddleBtm {border-left:1px solid #e2e2e2;border-right:1px solid #e2e2e2;border-bottom:1px solid #e2e2e2;border-radius:0 0 4px 4px;background-clip:padding-box;border-spacing:0}";

    EmailFormat += "#templateContainerMiddleBtm .bodyContent {padding-bottom:2em}";

    return EmailFormat += ".ii a[href] {color: inherit !important;}span > a, span > a[href] {color: inherit !important;}a > span, .ii a[href] > span {text-decoration: inherit !important;}</style>";

		 






	}

	private String sendEmail(EmailRequest request) {
		JavaMailEmailService j = new JavaMailEmailService();
		j.setEnabled(true);
		j.setHostname("smtp.gmail.com");
		j.setUsername("gis.click2mail@gmail.com");
		j.setPassword("Summer2017Projects*");
		j.setPort(587);
		j.setTlsEnabled(true);

		EmailBean email = new EmailBean("gis.click2mail@gmail.com", request.getEmail(), "Your Created Campaign",
				com.c2m.email.MediaType.TEXT_PLAIN_TYPE, methodE(request));

		try {
			j.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
			return e.getMessage();
		}

		return "success";
	}
}