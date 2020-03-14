package Utils;

import java.util.HashMap;
import java.util.Locale;

public class I18nMessage {

	static Locale local = Locale.FRANCE;

	HashMap<String,String> messageList ;

	static HashMap<String,String> messageFr = new HashMap<String, String>();
	static HashMap<String,String> messageEn = new HashMap<String, String>();


	public I18nMessage() {
		ini();
		messageList = messageFr;
	}

	public static void ini() {
		if(messageFr.isEmpty()) {
			iniI18n("style.list","List des style disponible : ","Avaible Style : ");
			
			iniI18n("style.create.error","Vous n'avez pas donne de nom correcte","Invalide name");
			iniI18n("style.cerate.sucesse","Style cree avec sucee","Suceffult style create");
			
			iniI18n("style.load.sucesse","Style load avec sucesse","Suceffult style load");
			iniI18n("style.load.sucesse","Style load avec sucesse","Suceffult style create");
			iniI18n("style.load.nofound","Il ny a pas de style {0} . Faite /style list pour voir les style disponible \n ou /style create <name> pour en cree un . ","they are no Style {0} . Do /style list to see the avaible style \n or /style create <name> for create a new one .");
			
			iniI18n("style.help","sytaxe de la commande Style : ","Style command syntaxe : ");
		}

	}


	public static void iniI18n(String key,String fr,String en) {
		messageFr.put(key, fr);
		messageEn.put(key, en);
	}



	public void changeLangue() {
		if(local==Locale.ENGLISH) {
			messageList = messageEn;
		}else {
			messageList = messageFr;
		}
	}


	public  String getMessage(String key) {
		
		if(messageList.containsKey(key)) {
			return messageList.get(key);
		}

		return key;
	}

	public static Locale getLocal() {
		return local;
	}

	public static void setLocal(Locale local) {
		I18nMessage.local = local;
		
	}


}
