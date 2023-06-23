
package com.example.application.views.list;

import com.example.application.data.entity.TipoOcorrencia;
import com.example.application.data.service.OcorrenciaService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.example.application.views.MainLayout;


@Route(value="ocorrencias", layout = MainLayout.class)
@PageTitle("CRUD_VAADIN")
public class OcorrenciaView extends VerticalLayout { 
    Grid<TipoOcorrencia> grid = new Grid<>(TipoOcorrencia.class); 
    TextField filterText = new TextField();
    OcorrenciaForm form;
    OcorrenciaService service;

    public OcorrenciaView (OcorrenciaService service) {
    	this.service = service;
        addClassName("ocorrencia-view"); 
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor(); 
    }

    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid); 
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

	private void configureForm() {
        form = new OcorrenciaForm (); 
        form.setWidth("20em");
        form.addSaveListener(this::saveContact); 
        form.addDeleteListener(this::deleteContact); 
        form.addCloseListener(e -> closeEditor()); 
    }
    
	
	//Metodo responsavel por salvar uma ocorrencia
		private void saveContact(OcorrenciaForm.SaveEvent event) {
			service.saveContact(event.getTipoOcorrencia());
		    updateList();
		    closeEditor();
		}

		private void deleteContact(OcorrenciaForm.DeleteEvent event) {
		    service.deleteContact(event.getTipoOcorrencia());
		    updateList();
		    closeEditor();
		}
		
		
	private void configureGrid() {
        grid.addClassNames("tipoOcorrencia-grid"); 
        grid.setSizeFull();
        grid.setColumns("id", "descricao"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        
        grid.asSingleSelect().addValueChangeListener(event ->
        editContact(event.getValue())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Buscar pelo Id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAOcorrenciaButton = new Button("Adicionar Ocorrencia");
        addAOcorrenciaButton.addClickListener(click -> addContact());

        var toolbar = new HorizontalLayout(filterText, addAOcorrenciaButton); 
        toolbar.addClassName("toolbar"); 
        return toolbar;
    }
    
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
    
    public void editContact(TipoOcorrencia ocorrencia) { 
        if (ocorrencia== null) {
            closeEditor();
        } else {
            form.setContact(ocorrencia);
            form.setVisible(true);
            addClassName("editing");
        }
    }
    
    private void closeEditor() {
        form.setContact(null);
        form.setVisible(false);
        removeClassName("editing");
    }
    
    private void addContact() { 
        grid.asSingleSelect().clear();
        editContact(new TipoOcorrencia());
    }
}

