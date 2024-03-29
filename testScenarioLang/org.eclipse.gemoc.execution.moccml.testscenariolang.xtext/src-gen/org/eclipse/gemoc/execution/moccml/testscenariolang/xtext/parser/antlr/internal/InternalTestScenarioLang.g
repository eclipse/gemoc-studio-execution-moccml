/*
 * generated by Xtext
 */
grammar InternalTestScenarioLang;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.services.TestScenarioLangGrammarAccess;

}

@parser::members {

 	private TestScenarioLangGrammarAccess grammarAccess;
 	
    public InternalTestScenarioLangParser(TokenStream input, TestScenarioLangGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Scenario";	
   	}
   	
   	@Override
   	protected TestScenarioLangGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleScenario
entryRuleScenario returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getScenarioRule()); }
	 iv_ruleScenario=ruleScenario 
	 { $current=$iv_ruleScenario.current; } 
	 EOF 
;

// Rule Scenario
ruleScenario returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getScenarioAccess().getScenarioAction_0(),
            $current);
    }
)	otherlv_1='Scenario' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getScenarioAccess().getScenarioKeyword_1());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getNameEStringParserRuleCall_2_0()); 
	    }
		lv_name_2_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.EString");
	        afterParserOrEnumRuleCall();
	    }

)
)?(((
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getModelImportsImportStatementParserRuleCall_3_0_0_0()); 
	    }
		lv_modelImports_3_0=ruleImportStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"modelImports",
        		lv_modelImports_3_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ImportStatement");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getClassImportsImportJavaStatementParserRuleCall_3_0_1_0()); 
	    }
		lv_classImports_4_0=ruleImportJavaStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"classImports",
        		lv_classImports_4_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ImportJavaStatement");
	        afterParserOrEnumRuleCall();
	    }

)
))((
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getModelImportsImportStatementParserRuleCall_3_1_0_0()); 
	    }
		lv_modelImports_5_0=ruleImportStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"modelImports",
        		lv_modelImports_5_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ImportStatement");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getClassImportsImportJavaStatementParserRuleCall_3_1_1_0()); 
	    }
		lv_classImports_6_0=ruleImportJavaStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"classImports",
        		lv_classImports_6_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ImportJavaStatement");
	        afterParserOrEnumRuleCall();
	    }

)
))*)?((
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getVariablesObjectVariableParserRuleCall_4_0_0()); 
	    }
		lv_variables_7_0=ruleObjectVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"variables",
        		lv_variables_7_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ObjectVariable");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getVariablesObjectVariableParserRuleCall_4_1_0()); 
	    }
		lv_variables_8_0=ruleObjectVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"variables",
        		lv_variables_8_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.ObjectVariable");
	        afterParserOrEnumRuleCall();
	    }

)
)*)?(
(
		{ 
	        newCompositeNode(grammarAccess.getScenarioAccess().getStatementSequenceStatementParserRuleCall_5_0()); 
	    }
		lv_statementSequence_9_0=ruleStatement		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getScenarioRule());
	        }
       		add(
       			$current, 
       			"statementSequence",
        		lv_statementSequence_9_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.Statement");
	        afterParserOrEnumRuleCall();
	    }

)
)+)
;





// Entry rule entryRuleImportStatement
entryRuleImportStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportStatementRule()); }
	 iv_ruleImportStatement=ruleImportStatement 
	 { $current=$iv_ruleImportStatement.current; } 
	 EOF 
;

// Rule ImportStatement
ruleImportStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='importModel' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getImportStatementAccess().getImportModelKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportStatementAccess().getImportURIEStringParserRuleCall_1_0()); 
	    }
		lv_importURI_1_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportStatementRule());
	        }
       		set(
       			$current, 
       			"importURI",
        		lv_importURI_1_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.EString");
	        afterParserOrEnumRuleCall();
	    }

)
)((
(
		lv_alias_2_0=	';' 
    {
        newLeafNode(lv_alias_2_0, grammarAccess.getImportStatementAccess().getAliasSemicolonKeyword_2_0_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getImportStatementRule());
	        }
       		setWithLastConsumed($current, "alias", lv_alias_2_0, ";");
	    }

)
)
    |(	otherlv_3='as' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getImportStatementAccess().getAsKeyword_2_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportStatementAccess().getAliasEStringParserRuleCall_2_1_1_0()); 
	    }
		lv_alias_4_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportStatementRule());
	        }
       		set(
       			$current, 
       			"alias",
        		lv_alias_4_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.EString");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_5=';' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getImportStatementAccess().getSemicolonKeyword_2_1_2());
    }
)))
;





// Entry rule entryRuleImportJavaStatement
entryRuleImportJavaStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportJavaStatementRule()); }
	 iv_ruleImportJavaStatement=ruleImportJavaStatement 
	 { $current=$iv_ruleImportJavaStatement.current; } 
	 EOF 
;

// Rule ImportJavaStatement
ruleImportJavaStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(((	otherlv_0='importClass' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getImportJavaStatementAccess().getImportClassKeyword_0_0_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportJavaStatementAccess().getImportedNamespaceQualifiedNameParserRuleCall_0_0_1_0()); 
	    }
		lv_importedNamespace_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportJavaStatementRule());
	        }
       		set(
       			$current, 
       			"importedNamespace",
        		lv_importedNamespace_1_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
))
    |(	otherlv_2='importPackage' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getImportJavaStatementAccess().getImportPackageKeyword_0_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportJavaStatementAccess().getImportedNamespaceQualifiedNameWithWildCardParserRuleCall_0_1_1_0()); 
	    }
		lv_importedNamespace_3_0=ruleQualifiedNameWithWildCard		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportJavaStatementRule());
	        }
       		set(
       			$current, 
       			"importedNamespace",
        		lv_importedNamespace_3_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.QualifiedNameWithWildCard");
	        afterParserOrEnumRuleCall();
	    }

)
)))	otherlv_4=';' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getImportJavaStatementAccess().getSemicolonKeyword_1());
    }
)
;





// Entry rule entryRuleObjectVariable
entryRuleObjectVariable returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getObjectVariableRule()); }
	 iv_ruleObjectVariable=ruleObjectVariable 
	 { $current=$iv_ruleObjectVariable.current; } 
	 EOF 
;

// Rule ObjectVariable
ruleObjectVariable returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='Variable' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getObjectVariableAccess().getVariableKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getObjectVariableAccess().getNameEStringParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleEString		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getObjectVariableRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.EString");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_2=':' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getObjectVariableAccess().getColonKeyword_2());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getObjectVariableAccess().getTypeJvmTypeReferenceParserRuleCall_3_0()); 
	    }
		lv_type_3_0=ruleJvmTypeReference		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getObjectVariableRule());
	        }
       		set(
       			$current, 
       			"type",
        		lv_type_3_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.JvmTypeReference");
	        afterParserOrEnumRuleCall();
	    }

)
)	otherlv_4=';' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getObjectVariableAccess().getSemicolonKeyword_4());
    }
)
;





// Entry rule entryRuleQualifiedNameWithWildCard
entryRuleQualifiedNameWithWildCard returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameWithWildCardRule()); } 
	 iv_ruleQualifiedNameWithWildCard=ruleQualifiedNameWithWildCard 
	 { $current=$iv_ruleQualifiedNameWithWildCard.current.getText(); }  
	 EOF 
;

// Rule QualifiedNameWithWildCard
ruleQualifiedNameWithWildCard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQualifiedNameWithWildCardAccess().getQualifiedNameParserRuleCall_0()); 
    }
    this_QualifiedName_0=ruleQualifiedName    {
		$current.merge(this_QualifiedName_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }

	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildCardAccess().getFullStopKeyword_1()); 
    }

	kw='*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildCardAccess().getAsteriskKeyword_2()); 
    }
)
    ;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
    }
    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





// Entry rule entryRuleJvmTypeReference
entryRuleJvmTypeReference returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getJvmTypeReferenceRule()); }
	 iv_ruleJvmTypeReference=ruleJvmTypeReference 
	 { $current=$iv_ruleJvmTypeReference.current; } 
	 EOF 
;

// Rule JvmTypeReference
ruleJvmTypeReference returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getJvmTypeReferenceAccess().getJvmAnyTypeReferenceAction_0(),
            $current);
    }
)(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getJvmTypeReferenceRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getJvmTypeReferenceAccess().getTypeJvmTypeCrossReference_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleStatement
entryRuleStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStatementRule()); }
	 iv_ruleStatement=ruleStatement 
	 { $current=$iv_ruleStatement.current; } 
	 EOF 
;

// Rule Statement
ruleStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getStatementAccess().getMseStatementParserRuleCall_0()); 
    }
    this_MseStatement_0=ruleMseStatement
    { 
        $current = $this_MseStatement_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getStatementAccess().getRewritingRuleCallStatementParserRuleCall_1()); 
    }
    this_RewritingRuleCallStatement_1=ruleRewritingRuleCallStatement
    { 
        $current = $this_RewritingRuleCallStatement_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleMseStatement
entryRuleMseStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getMseStatementRule()); }
	 iv_ruleMseStatement=ruleMseStatement 
	 { $current=$iv_ruleMseStatement.current; } 
	 EOF 
;

// Rule MseStatement
ruleMseStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='expect' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getMseStatementAccess().getExpectKeyword_0());
    }
(
    {
        $current = forceCreateModelElement(
            grammarAccess.getMseStatementAccess().getMseStatementAction_1(),
            $current);
    }
)(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMseStatementRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getMseStatementAccess().getClocksClockCrossReference_2_0()); 
	}

)
)(	otherlv_3='and' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getMseStatementAccess().getAndKeyword_3_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getMseStatementRule());
	        }
        }
	otherlv_4=RULE_ID
	{
		newLeafNode(otherlv_4, grammarAccess.getMseStatementAccess().getClocksClockCrossReference_3_1_0()); 
	}

)
))*(	otherlv_5=';' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getMseStatementAccess().getSemicolonKeyword_4());
    }
)?)
;





// Entry rule entryRuleRewritingRuleCallStatement
entryRuleRewritingRuleCallStatement returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRewritingRuleCallStatementRule()); }
	 iv_ruleRewritingRuleCallStatement=ruleRewritingRuleCallStatement 
	 { $current=$iv_ruleRewritingRuleCallStatement.current; } 
	 EOF 
;

// Rule RewritingRuleCallStatement
ruleRewritingRuleCallStatement returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='execute' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getRewritingRuleCallStatementAccess().getExecuteKeyword_0());
    }
(
    {
        $current = forceCreateModelElement(
            grammarAccess.getRewritingRuleCallStatementAccess().getRewritingRuleCallStatementAction_1(),
            $current);
    }
)((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRewritingRuleCallStatementRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getRewritingRuleCallStatementAccess().getObjectVariableObjectVariableCrossReference_2_0_0()); 
	}

)
)	otherlv_3='.' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getRewritingRuleCallStatementAccess().getFullStopKeyword_2_1());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRewritingRuleCallStatementRule());
	        }
        }
	otherlv_4=RULE_ID
	{
		newLeafNode(otherlv_4, grammarAccess.getRewritingRuleCallStatementAccess().getMethodJvmOperationCrossReference_2_2_0()); 
	}

)
)(	otherlv_5='(' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getRewritingRuleCallStatementAccess().getLeftParenthesisKeyword_2_3_0());
    }
((
(
		{ 
	        newCompositeNode(grammarAccess.getRewritingRuleCallStatementAccess().getParametersVariableParserRuleCall_2_3_1_0_0()); 
	    }
		lv_parameters_6_0=ruleVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRewritingRuleCallStatementRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_6_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.Variable");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_7=',' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getRewritingRuleCallStatementAccess().getCommaKeyword_2_3_1_1_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getRewritingRuleCallStatementAccess().getParametersVariableParserRuleCall_2_3_1_1_1_0()); 
	    }
		lv_parameters_8_0=ruleVariable		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRewritingRuleCallStatementRule());
	        }
       		add(
       			$current, 
       			"parameters",
        		lv_parameters_8_0, 
        		"org.eclipse.gemoc.execution.moccml.testscenariolang.xtext.TestScenarioLang.Variable");
	        afterParserOrEnumRuleCall();
	    }

)
))*)*	otherlv_9=')' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getRewritingRuleCallStatementAccess().getRightParenthesisKeyword_2_3_2());
    }
)?)(	otherlv_10=';' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getRewritingRuleCallStatementAccess().getSemicolonKeyword_3());
    }
)?)
;





// Entry rule entryRuleVariable
entryRuleVariable returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getVariableRule()); }
	 iv_ruleVariable=ruleVariable 
	 { $current=$iv_ruleVariable.current; } 
	 EOF 
;

// Rule Variable
ruleVariable returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getVariableAccess().getIntegerLiteralParserRuleCall_0()); 
    }
    this_IntegerLiteral_0=ruleIntegerLiteral
    { 
        $current = $this_IntegerLiteral_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getVariableAccess().getStringLiteralParserRuleCall_1()); 
    }
    this_StringLiteral_1=ruleStringLiteral
    { 
        $current = $this_StringLiteral_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getVariableAccess().getEObjectRefParserRuleCall_2()); 
    }
    this_EObjectRef_2=ruleEObjectRef
    { 
        $current = $this_EObjectRef_2.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleEObjectRef
entryRuleEObjectRef returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getEObjectRefRule()); }
	 iv_ruleEObjectRef=ruleEObjectRef 
	 { $current=$iv_ruleEObjectRef.current; } 
	 EOF 
;

// Rule EObjectRef
ruleEObjectRef returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getEObjectRefRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getEObjectRefAccess().getObjectEObjectCrossReference_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)
;





// Entry rule entryRuleStringLiteral
entryRuleStringLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStringLiteralRule()); }
	 iv_ruleStringLiteral=ruleStringLiteral 
	 { $current=$iv_ruleStringLiteral.current; } 
	 EOF 
;

// Rule StringLiteral
ruleStringLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_STRING
		{
			newLeafNode(lv_value_0_0, grammarAccess.getStringLiteralAccess().getValueSTRINGTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStringLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
)
;





// Entry rule entryRuleIntegerLiteral
entryRuleIntegerLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntegerLiteralRule()); }
	 iv_ruleIntegerLiteral=ruleIntegerLiteral 
	 { $current=$iv_ruleIntegerLiteral.current; } 
	 EOF 
;

// Rule IntegerLiteral
ruleIntegerLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_INT
		{
			newLeafNode(lv_value_0_0, grammarAccess.getIntegerLiteralAccess().getValueINTTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getIntegerLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.xtext.common.Terminals.INT");
	    }

)
)
;





// Entry rule entryRuleEString
entryRuleEString returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getEStringRule()); } 
	 iv_ruleEString=ruleEString 
	 { $current=$iv_ruleEString.current.getText(); }  
	 EOF 
;

// Rule EString
ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_STRING_0=RULE_STRING    {
		$current.merge(this_STRING_0);
    }

    { 
    newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
    }

    |    this_ID_1=RULE_ID    {
		$current.merge(this_ID_1);
    }

    { 
    newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
    }
)
    ;











RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_INT : ('0'..'9')+;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


