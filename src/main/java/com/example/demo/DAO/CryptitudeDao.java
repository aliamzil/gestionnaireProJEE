package com.example.demo.DAO;

import java.security.Key;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Repository;

@Repository
public class CryptitudeDao {

	int p = 7829, q = 7841;

	public int pgcd(int r, int phin) {
		// PDCD entre r et phin
		while (r != phin) {
			if (r > phin) {
				r = r - phin;
			} else {
				phin = phin - r;
			}
		}
		return r;
	}

	public int[] clepublic(int p, int q) {
		int[] maCle = { 0, 0 };
		int j = 0;

		int n = p * q;
		int phin = (p - 1) * (q - 1);
		int o = (p > q) ? p : q;
		int pgcd1 = 1000000;

		for (int i = o; i <= phin; i++) {
			j = i;
			pgcd1 = pgcd(i, phin);
			if (pgcd1 == 1)
				break;
		}

		maCle[0] = j;
		maCle[1] = n;

		return maCle;
	}

	public String cryptage(String pass) {
		String res = "", aa = "", b1 = "";
		int a = 0;
		int[] macle = this.clepublic(p, q);

		for (char ch : pass.toCharArray()) {
			a = (int) ch;
			a = (a * 10000) + p;
			a = a % q;
			aa = (a < 1000) ? "0" : "";
			b1 = (res == "") ? "" : " ";
			res = res + b1 + aa + a;
		}

		return res;
	}

//	Crypter le mot de passe :

	public String encrypt(String password, String key) {
		try {
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.ENCRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			return null;
		}
	}

//		D�crypter le mot de passe :

	public String decrypt(String password, String key) {
		try {
			Key clef = new SecretKeySpec(key.getBytes("ISO-8859-2"), "Blowfish");
			Cipher cipher = Cipher.getInstance("Blowfish");
			cipher.init(Cipher.DECRYPT_MODE, clef);
			return new String(cipher.doFinal(password.getBytes()));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
