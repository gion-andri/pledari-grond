package de.uni_koeln.spinfo.maalr.conjugator.verbs;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

public class MapVerbsTest {
	private static MapVerbs mapper;

	@BeforeClass
	public static void initialize() {
		mapper = new MapVerbs();

	}

	@Test
	public void testAddConjugations() throws IOException {

		List<String> list = mapper.addConjugations("data_sm_ov.tab");
		List<String> list_one = new ArrayList<>();

		Set<String> set = new LinkedHashSet<String>(list);

		VerbsIO.printSet(set, "data_sm_set");
		VerbsIO.printList(list, "data_sm");

	}

	@Test
	public void test() {

		String s = "5596	ausgesprochen		adj	veir				H	ausgesproche	D	zzausgesprochen			typisch	";

		String[] array = s.split("\\t");

		if (array[3].equals("adj")) {

			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}

	@Test
	public void testRegex() {

		String s = "";

		if (mapper.isEmpty(s)) {
			System.out.println("hello");
		}

	}

	@Test
	public void formatIrregulars() throws IOException {

		List<HashMap<String, String>> list_irregulars = mapper
				.parseIrregulars("irregulars.txt");

		// VerbsIO.printList(list_irregulars, "irreg_format_map");
	}

	@Test
	public void addReflexiva() throws IOException {

		List<Reflex> reflex = mapper.cleanReflexives();

		List<String> regulars = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "reg.txt"),
				Charset.forName("UTF8"));

		List<String> cr = regulars;

		List<String> vw = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "vw.txt"),
				Charset.forName("UTF8"));

		List<String> cvw = vw;

		List<String> esch = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "reg_esch.txt"),
				Charset.forName("UTF8"));

		List<String> cesch = esch;

		for (Reflex r : reflex) {

			if (cr.contains(r.getVerb())) {
				System.out.println(r.getPrefix() + r.getVerb() + "\t" + "reg");
				regulars.add(r.getPrefix() + r.getVerb());
			}

			if (cvw.contains(r.getVerb())) {
				System.out.println(r.getPrefix() + r.getVerb() + "\t" + "vw");
				vw.add(r.getPrefix() + r.getVerb());
			}

			if (cesch.contains(r.getVerb())) {
				System.out.println(r.getPrefix() + r.getVerb() + "\t" + "wsch");
				esch.add(r.getPrefix() + r.getVerb());
			}

		}

		VerbsIO.printList(regulars, "regulars_ref");
		VerbsIO.printList(vw, "vw_ref");
		VerbsIO.printList(esch, "esch_ref");

	}

	@Test
	public void showDups() throws IOException {

		List<HashMap<String, String>> list_irregulars = mapper
				.parseIrregulars("irregulars.txt");

		List<String> toGenerate = new ArrayList<>();
		List<String> regulars = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "reg.txt"),
				Charset.forName("UTF8"));

		List<String> vw = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "vw.txt"),
				Charset.forName("UTF8"));

		List<String> esch = Files.readAllLines(
				Paths.get(VerbsIO.input_dir + "reg_esch.txt"),
				Charset.forName("UTF8"));

		toGenerate.addAll(regulars);
		toGenerate.addAll(vw);
		toGenerate.addAll(esch);

		Set<String> set = new HashSet<>();
		for (String s : toGenerate) {

			String[] splitted = s.split("\t");

			set.add(splitted[0]);

		}

		for (HashMap<String, String> m : list_irregulars) {

			String v = m.get("verb");

			if (set.contains(v)) {

				System.out.println("DUP: " + v);

			}

		}

	}

}
