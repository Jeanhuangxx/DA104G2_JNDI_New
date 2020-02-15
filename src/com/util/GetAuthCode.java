package com.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAuthCode {
	// 隨機函數產生密碼
		public static String getAuthCode() { // 0~9：30~39。A~Z：41~5A。a~z：61~7A。
			String verificationCode = ""; // 創建驗證碼字串，初始值給""。
			int randomNum; // 創建一個存放亂數的變數
			for (int i = 1; i <= 6; i++) { // i=1~8，分別代表第i個驗證碼
				randomNum = (int) (Math.random() * 62); // 產生0~61亂數，共有62個亂數(數字10+大寫26+小寫26)
				if (randomNum <= 9) { // (數字)進入此條件的亂數範圍為randomNum=0~9，共10個數字，分別代表驗證碼的0~9
					verificationCode += randomNum; // 數字無須轉換為字元，直接寫入驗證碼
				} else if (randomNum <= 35) { // (大寫字母)進入此條件的亂數範圍為randomNum=10~35，共26個數字，分別代表驗證碼的A~Z
					verificationCode += (char) ('\u0041' + (randomNum - 10)); // 大寫A為'\u0041'，當+(randomNum-10)=0時，為A，而當+(randomNum-10)=+25時，為Z。
				} else { // (小寫字母)進入此條件的亂數範圍為randomNum=36~61，共26個數字，分別代表驗證碼的a~z
					verificationCode += (char) ('\u0061' + (randomNum - 36)); // 小寫a為'\u0061'，當+(randomNum-36)=0時，為a，而當+(randomNum-36)=+25時，為z。
				}
			}
			
			//out.print("verificationCode");			
			System.out.println("本次隨機產生驗證碼為：" + "\n" + verificationCode); // 印出驗證碼
			return verificationCode;
		}
}
