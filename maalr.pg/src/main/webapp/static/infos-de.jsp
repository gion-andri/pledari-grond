<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="java.util.Locale" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="de.uni_koeln.spinfo.maalr.mongo.stats.DictionaryStatistics" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="<%=session.getAttribute("pl")%>" />
<fmt:setBundle basename="de.uni_koeln.spinfo.maalr.webapp.i18n.text" />

<h1>Allgemeine Informationen</h1>

<p>
	<strong>
		Diese Online-Version des Vocabulari surmiran basiert auf dem Vocabulari surmiran-tudestg/Wörterbuch Deutsch-Surmiran, Version CD, Lizenzausgabe © Lehrmittel Graubünden; technische Bearbeitung der sprachlichen Daten © 2004 Lia Rumantscha; Redaktion Faust Signorell et al.
	</strong>
</p>

<p>
	<%
		// String languageTag = (String) session.getAttribute("locale");
		// Locale locale = Locale.forLanguageTag(languageTag);
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.forLanguageTag(languageTag));;
	%>
	<fmt:message key="maalr.index.entry_count" var="numberOfEntries">
		<fmt:param><%=nf.format(DictionaryStatistics.getStatistics().entryCounter)%></fmt:param>
	</fmt:message>
	${numberOfEntries}.
</p>

<p>
	Der Wortschatz einer Sprache hat keine Grenzen. So lange die Sprache lebt, wächst und entwickelt sich dieser weiter und passt sich organisch den Kommunikationsbedürfnissen der Sprachgemeinschaft an. Alle Nutzerinnen und Nutzer sind eingeladen, zur Entwicklung des Vocabulari surmiran online beizutragen. Wer ein Wort oder einen Ausdruck nicht findet, kann über das entsprechende Formular (es erscheint durch Anklicken des Buttons "Neuer Eintrag") Vorschläge machen. Wer einen Fehler entdeckt, kann über ein weiteres Formular (es erscheint durch Anklicken des Buttons "Bearbeiten") eine Änderung vorschlagen oder eine Anmerkung versenden.	
</p>

<p>
	<b> © Lia Rumantscha, CH-7000 Chur</b>
</p>

<p>
	<b>Konzept und Umsetzung</b>
	<br>
	Lia Rumantscha, Daniel Telli
</p>

<p>
	<b>Aktuelle Redaktion</b>
	<br>
	Reto Capeder
</p>

<p>
	<b>Programme</b>
	<br>
	Jürgen Rolshoven
	<br>
	Claes Neuefeind
	<br>
	Stephan Schwiebert<br>
	Mihail Atanassov
	<br> 
	(Institut für Linguistik, Sprachliche Informationsverarbeitung, Universität zu Köln)
</p>

<p>
	<b>Grafisches Design</b>
	<br>
	Remo Caminada, www.remocaminada.com
</p>
