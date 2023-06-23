package com.example.application.views.list;



import com.example.application.data.entity.TipoActividade;
import com.example.application.data.service.ActividadeService;
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


@Route(value="", layout = MainLayout.class)
@PageTitle("CRUD_VAADIN")
public class ActividadeView extends VerticalLayout { 
    Grid<TipoActividade> grid = new Grid<>(TipoActividade.class); 
    TextField filterText = new TextField();
    ActividadeForm form;
    ActividadeService service;

    public ActividadeView(ActividadeService service) {
    	this.service = service;
        addClassName("actividade-view"); 
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
        form = new ActividadeForm(); 
        form.setWidth("25em");
        form.addSaveListener(this::saveContact); 
        form.addDeleteListener(this::deleteContact); 
        form.addCloseListener(e -> closeEditor()); 
    }
	
	//Metodo responsavel por salvar uma actividade
	private void saveContact(ActividadeForm.SaveEvent event) {
	    //ActividadeService service = null;
		service.saveContact(event.getTipoActividade());
	    updateList();
	    closeEditor();
	}

	private void deleteContact(ActividadeForm.DeleteEvent event) {
		//ActividadeService service = null;
	    service.deleteContact(event.getTipoActividade());
	    updateList();
	    closeEditor();
	}
	
	private void configureGrid() {
        grid.addClassNames("tipoActividade-grid"); 
        grid.setSizeFull();
        grid.setColumns("id", "descricao", "avaliacao"); 
        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
        
        grid.asSingleSelect().addValueChangeListener(event ->
        editContact(event.getValue())); 
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Buscar pelo Id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addActividadeButton = new Button("Adicionar Actividade");
        addActividadeButton.addClickListener(click -> addContact());

        var toolbar = new HorizontalLayout(filterText, addActividadeButton); 
        toolbar.addClassName("toolbar"); 
        return toolbar;
    }
    
    private void updateList() {
        grid.setItems(service.findAllContacts(filterText.getValue()));
    }
    
    public void editContact(TipoActividade actividade) { 
        if (actividade == null) {
            closeEditor();
        } else {
            form.setContact(actividade);
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
        editContact(new TipoActividade());
    }
}
