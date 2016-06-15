package rs.ac.bg.etf.pp1.pd110349d;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.pd110349d.util.Log4JUtils;

public class MJParserTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			//File sourceCode = new File("test/program.mj");
			File sourceCode = new File("test/" + args[0]);
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        log.info("A");
	        log.info("Global var count = " + p.action_obj.globalVarCount);
	        log.info("Local var count = " + p.action_obj.localVarCount);
	        log.info("Global const count = " + p.action_obj.globalConstCount);
	        log.info("Global array count = " + p.action_obj.globalArrayCount);
	        
	        log.info("B");
	        log.info("Functions in main count = " + p.action_obj.mainFuncDefCnt);
	        log.info("Statement block count = " + p.action_obj.statementBlockCnt);
	        log.info("Function calls in main count = " + p.action_obj.mainFuncCall);
	        log.info("Formal arguments declaration count = " + p.action_obj.formArgDecl);
	        
	        log.info("C");
	        log.info("Nested class count = " + p.action_obj.nestedClassDefCnt);
	        log.info("Nested class methods count = " + p.action_obj.nestedMethodDefCnt);
	        log.info("Nested class field count = " + p.action_obj.nestedFieldDefCnt);
	        
	        
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
